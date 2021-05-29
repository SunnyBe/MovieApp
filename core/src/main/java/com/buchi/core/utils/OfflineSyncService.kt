package com.buchi.core.utils

import android.content.Context
import android.util.Log
import com.buchi.core.utils.OfflineSyncService.Builder
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.coroutines.CoroutineContext

/**
 * Offline sync processes can be done with [OfflineSyncService]. Functions basically to fetch data
 * from the internet and updates the cache.
 * [OfflineSyncService] provides a builder [Builder] to set configurations for an instance of this
 * class. The build method of [Builder] in turn creates a new instance of OfflineSyncService.
 * Take Note: Consumer class (usually Repository Implementation) is expected to provide the network
 * request and caching process
 * implementations using the [offlineSyncCallback]
 * @property N is the class representing the Network response expected to be cached.
 * @property C is the class representing the DataBase Data Model.
 * N and C could be the same in case the same network response Model is been saved in the DB.
 */
class OfflineSyncService<N, C> private constructor(
    private val toCache: Boolean?,
    private val cacheWihBackgroundService: Boolean?,
    private val offlineSyncCallback: OfflineSyncServiceCallback<N, C>?
) {

    // Boolean value set from the customNetworkCheck provided by the consumer.
    @Volatile
    private var isNetworkAvailable: Boolean? = null

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
     */
    fun warn(error: suspend (Throwable) -> Unit) = apply {

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
                Log.d(javaClass.simpleName, "Internet is not available. Last data will be served")
                // Todo Pass this warning through to the offlineService instance
//                warn(Throwable("Internet is not available. Last data will be served"))
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
                // Todo Pass this warning through to the offlineService instance
//                warn(Throwable("Internet is not available. Last data will be served"))
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
        val networkResponse =  netWorkData(dispatcher)
        // When internet is available
        if (toCache != null && !toCache) throw RuntimeException("You can't get a cached data, since you didn't specify toCache=true in OfflineSyncService.Build")
        if (networkResponse == null) throw RuntimeException("Cannot proceed since Network response has value as null.")
        offlineSyncCallback?.cacheNetworkResponse(networkResponse)
    }

    /**
     * A Builder class for [OfflineSyncService] to set configuration and important implementation of
     * network and data caching processes via the [OfflineSyncServiceCallback]
     *
     */
    data class Builder<N, C>(
        @Volatile
        var toCache: Boolean? = false,
        @Volatile
        var cacheWihBackgroundService: Boolean? = false,
        @Volatile
        var offlineSyncCallback: OfflineSyncServiceCallback<N, C>? = null,
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

        fun build(): OfflineSyncService<N, C> =
            OfflineSyncService(toCache, cacheWihBackgroundService, offlineSyncCallback)
    }

    interface OfflineSyncServiceCallback<N, out C> {
        suspend fun networkCall(): N
        suspend fun cachedData(): C
        suspend fun cacheNetworkResponse(networkResponse: N)
    }
}