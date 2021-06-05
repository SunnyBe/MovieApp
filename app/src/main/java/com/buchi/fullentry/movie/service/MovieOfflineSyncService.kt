package com.buchi.fullentry.movie.service

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

/**
 * Offline Sync Service to perform data synching in the background. Network call is made from the
 * background and Synced to Cache.
 * NB: Not done yet.
 */
class MovieOfflineSyncService(appContext: Context, workerParams: WorkerParameters): Worker(appContext, workerParams) {
//    @Inject
//    lateinit var movieRepository: MovieRepository

    override fun doWork(): Result {
        Log.d(javaClass.simpleName, "Confirm Work is done!: ${javaClass.simpleName}")
//        synchData<N, C>(repository)
        return Result.success()
    }
}