package com.buchi.core.utils

import android.accounts.NetworkErrorException
import android.content.Context
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.RuntimeException
import java.security.InvalidParameterException
import java.util.*
import java.util.concurrent.Flow
import kotlin.coroutines.CoroutineContext

/**
 * Service created to majorly perform offline data rendering.
 * NB: This is not done yet
 */
class OfflineFirstService(private val context: Context) {
    @Volatile
    private var offlineCache = false    // Offline cache is set to false by default
    companion object : SingletonHolder<OfflineFirstService, Context>(::OfflineFirstService)

    suspend fun <N> networkRequest(request: suspend () -> N): OfflineFirstService {
        if (InternetReachability.hasInternetConnected(context)) {
            request()
            return this
        } else {
            throw NetworkErrorException("No internet")
        }
    }

    suspend fun<T, K> dataFromCache(cacheSyncCallback: CacheSyncCallback<T, K>): K? {
        return withContext(Dispatchers.IO) {
            if (!offlineCache) throw RuntimeException("isOfflineCache value is false. Set value to true  in Builder.config() function!")
//        cacheSyncCallback.networkCall()
//        cacheSyncCallback.syncFromNetworkCall()
            val entity = cacheSyncCallback.networkCacheEntity()
            entity
        }
    }

    suspend fun<T, K> makeNetworkCall(syncCallback: CacheSyncCallback<T, K>, dispatcher: CoroutineContext): OfflineFirstService {
        return withContext(dispatcher) {
            if (!InternetReachability.isInternetAvailable(context)) throw RuntimeException("No internet available!")
            syncCallback.networkCall()
            this@OfflineFirstService
        }
    }

    inner class Builder {
        fun config(isOfflineCache: Boolean = false): Builder {
            offlineCache = isOfflineCache
            return this
        }
        fun build(): OfflineFirstService {
            return this@OfflineFirstService
        }
    }

    /**
     * Process cache and network synching from the consuming class.
     * @param T usually the viewState to return from each of the processes. While implementing this
     * interface, T must be returned from every process.
     */
    interface CacheSyncCallback<T, K> {
        suspend fun networkCall(): T?
        suspend fun syncFromNetworkCall(): T?
        suspend fun networkCacheEntity(): K?
//        fun checkCache(): T
//        fun synchFromNetwork(): T
//        fun<K> cacheNetworkRequest(): K
    }
}