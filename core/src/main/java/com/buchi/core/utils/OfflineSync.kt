package com.buchi.core.utils

import android.content.Context
import android.util.Log
import androidx.work.*
import com.buchi.core.utils.OfflineSync.Builder
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.coroutines.CoroutineContext

/**
 * Offline sync processes can be done with [OfflineSync]. Functions basically to fetch data
 * from the internet and updates the cache.
 * [OfflineSync] provides a builder [Builder] to set configurations for an instance of this
 * class. The build method of [Builder] in turn creates a new instance of OfflineSyncService.
 * Take Note: Consumer class (usually Repository Implementation) is expected to provide the network
 * request and caching process
 * implementations using the [offlineSyncCallback]
 * @property N is the class representing the Network response expected to be cached.
 * @property C is the class representing the DataBase Data Model.
 * N and C could be the same in case the same network response Model is been saved in the DB.
 */
class OfflineSync<N, C> private constructor(
    private val toCache: Boolean?,
    private val cacheWihBackgroundService: Boolean?,
    private val offlineSyncCallback: OfflineSyncServiceCallback<N, C>?
) {

    // Boolean value set from the customNetworkCheck provided by the consumer.
    @Volatile
    private var isNetworkAvailable: Boolean? = null

    // Service Related error to notify Consumer of error.
    @Volatile
    private var serviceError: (suspend (Throwable) -> Unit)? = null

    /**
     * Update isNetworkAvailable and proceed with using the same OfflineSyncService instance.
     * @param isNetworkAvailable callback to take in Network check implementation from consumer.
     */
    fun customNetworkCheck(isNetworkAvailable: () -> Boolean) = apply {
        this.isNetworkAvailable = isNetworkAvailable()
    }

    /**
     * Update this instance of an error. This is to handle error notification while implementation is
     * continued. For example, Network unavailability can notify consumer and still proceed to send
     * last cached data to consumer.
     * @param warningCallback callback to notify consumer of error and latest cached data.
     */
    fun warn(warningCallback: suspend (error: Throwable) -> Unit) =
        apply {
            serviceError = warningCallback
        }

    /**
     * Skip the cache process and send Network response to the consumer.
     * @param dispatcher context to process the coroutines suspend function.
     */
    suspend fun netWorkData(dispatcher: CoroutineContext): N? {
        return withContext(dispatcher) {
            offlineSyncCallback?.networkCall()
        }
    }

    /**
     * Returns the latest cached data whether there was a successful Network update or not.
     * Process network with cache processing without expecting a context from the Consumer. This will flag an
     * error only if Consumer didn't provide a custom network check implementation prior to calling
     * this function.
     * @param dispatcher context to process the coroutines suspend function.
     */
    suspend fun cachedData(dispatcher: CoroutineContext): C? {
        return withContext(dispatcher) {
            if (isNetworkAvailable == null) throw RuntimeException("Context or a network check was not provided. Provide either a context or your network check implementation to fetch cached data")
            if (isNetworkAvailable as Boolean) {
                // When internet is available
                makeNetworkCallAndCacheResponse(dispatcher)
            } else {
                Log.d(javaClass.simpleName, "Internet is not available. Last data will be served.")
                val throwable = Throwable("Internet is not available. Last data will be served.")
                serviceError?.let { cause -> cause(throwable) }
            }
            offlineSyncCallback?.cachedData()
        }
    }

    /**
     * Returns the latest cached data whether there was a successful Network update or not.
     * Process network and cache processing while expecting a context from the Consumer. Internet
     * check is done on behalf of the consumer.
     * @param context used for the network availability check.
     * @param dispatcher context to process the coroutines suspend function.
     */
    suspend fun cachedData(context: Context?, dispatcher: CoroutineContext): C? {
        return withContext(dispatcher) {
            if (context != null && InternetReachability.isInternetAvailable(context)) {
                // When internet is available
                makeNetworkCallAndCacheResponse(dispatcher)
            } else {
                Log.d(javaClass.simpleName, "Internet is not available. Last data will be served")
                serviceError?.let { cause -> cause(Throwable("Internet is not available. Last data will be served")) }
            }
            offlineSyncCallback?.cachedData()
        }
    }

    /**
     * Predefined condition that must be met before calling this method is to confirm availability
     * of internet connection. Network request will DEFINITELY fail if this condition is not met.
     * @param dispatcher context to run suspending function in.
     */
    private suspend fun makeNetworkCallAndCacheResponse(dispatcher: CoroutineContext) {
        withContext(dispatcher) {
            val networkResponse = netWorkData(dispatcher)
            // When internet is available
            if (toCache != null && !toCache) throw RuntimeException("You can't get a cached data, since you didn't specify toCache=true in OfflineSyncService.Build")
            if (networkResponse == null) throw RuntimeException("Cannot proceed since Network response has value as null.")
            offlineSyncCallback?.cacheNetworkResponse(networkResponse)
        }
    }

    /**
     * A Builder class for [OfflineSync] to set configuration and important implementation of
     * network and data caching processes via the [OfflineSyncServiceCallback]
     *
     */
    data class Builder<N, C>(
        @Volatile
        var toCache: Boolean? = false,
        @Volatile
        var cacheWihBackgroundService: Boolean? = false,
        @Volatile
        var offlineSyncCallback: OfflineSyncServiceCallback<N, C>? = null
    ) {

        fun toCache(toCache: Boolean?) = apply {
            this.toCache = toCache
        }

        fun cacheWihBackgroundService(cacheWihBackgroundService: Boolean) = apply {
            this.cacheWihBackgroundService = cacheWihBackgroundService
        }

        fun offlineSyncCallback(offlineSyncCallback: OfflineSyncServiceCallback<N, C>) = apply {
            this.offlineSyncCallback = Objects.requireNonNull(
                offlineSyncCallback,
                "OfflineSyncServiceCallback is null You need to specify your callback implementation for the interface: OfflineSyncCallback!"
            )
        }

        inline fun<reified B: Worker> startOfflineService(context: Context?) = apply {
            if (cacheWihBackgroundService != null && !cacheWihBackgroundService!!)
                throw Throwable("Sync Builder class has cacheWihBackgroundService value to be false. Set it to be true")
            startBackgroundService<B>(context)
        }

        inline fun<reified W: Worker> startBackgroundService(context: Context?) {
            if (context == null) throw NullPointerException("Context must not be null. Provide a context to start background service.")
            val syncWorkRequest: WorkRequest = OneTimeWorkRequestBuilder<W>()
                .build()
            WorkManager.getInstance(context)
                .enqueue(syncWorkRequest)
        }

        fun build(): OfflineSync<N, C> =
            OfflineSync(toCache, cacheWihBackgroundService, offlineSyncCallback)
    }

    /**
     * [OfflineSyncServiceCallback] Interface to process important implementations required for Syncing data from Network to local
     * Cache. Expected implementation includes network call, caching as well as how the Consumer
     * wants the caching to be done.
     * Consumer's implementation of [OfflineSyncServiceCallback] must be provided and must be working
     * to achieve syncing.
     * Since these functions could be long running tasks, they are made suspend functions and are
     * expected to run on a background thread.
     */
    interface OfflineSyncServiceCallback<N, out C> {
        /**
         * User specified Network call that returns [N] which is the Network data Model or DTO
         * @return Network data Model or DTO
         */
        suspend fun networkCall(): N

        /**
         * User specified caching process that returns [C] which is the Database data Model.
         * @return Database data Model.
         */
        suspend fun cachedData(): C

        /**
         * User specified Network response caching. It is necessary to inject the Network response
         * which has class type [N] as a parameter to this function.
         * @return Network data Model or DTO
         */
        suspend fun cacheNetworkResponse(networkResponse: N)
    }

    data class NoInternetException(override val message: String, val throws: Throwable?) :
        Throwable() {

    }
}