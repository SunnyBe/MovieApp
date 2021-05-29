package com.buchi.fullentry.movie.data

import android.content.Context
import android.util.Log
import com.buchi.core.utils.InternetReachability
import com.buchi.core.utils.OfflineSyncService
import com.buchi.core.utils.ResultState
import com.buchi.fullentry.movie.data.cache.MovieDao
import com.buchi.fullentry.movie.data.cache.MovieData
import com.buchi.fullentry.movie.data.network.MovieDto
import com.buchi.fullentry.movie.domain.toData
import com.buchi.fullentry.movie.domain.toEntity
import com.buchi.fullentry.movie.presentation.moviedetail.MovieDetailViewState
import com.buchi.fullentry.movie.presentation.movielist.MovieListViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@ExperimentalCoroutinesApi
class MoviesRepositoryImpl @Inject constructor(
    private val context: Context,
    private val network: MovieService,
    private val cache: MovieDao,
    private val dispatcher: CoroutineContext = Dispatchers.IO
) : MovieRepository {

    private val movieListOfflineServiceBuilder: OfflineSyncService.Builder<List<MovieDto>, List<MovieData>> by lazy {
        OfflineSyncService.Builder<List<MovieDto>, List<MovieData>>()
            .toCache(true)
            .cacheWihBackgroundService(false)
    }

    override fun fetchList(id: Int?): Flow<ResultState<MovieListViewState>> {
        return flow {
            val movieOfflineService = movieListOfflineServiceBuilder.offlineSyncCallback(object :
                OfflineSyncService.OfflineSyncServiceCallback<List<MovieDto>, List<MovieData>> {
                override suspend fun networkCall(): List<MovieDto> {
                    return network.listById(id = id).results
                }

                override suspend fun cacheNetworkResponse(networkResponse: List<MovieDto>) {
                    val responseToData = networkResponse.map { it.toData() }
                    cache.insertAll(*responseToData.toTypedArray())
                }

                override suspend fun cachedData(): List<MovieData> {
                    return cache.getAll()
                }
            })
            val cachedList = movieOfflineService.build()
                .customNetworkCheck { InternetReachability.isInternetAvailable(context) }
                .warn { error ->
//                    emit(ResultState.error<MovieListViewState>(error))
                }
                .cachedData(dispatcher)?.map { it.toEntity() }
            Log.d(javaClass.simpleName, "Cached List: $cachedList")
            emit(ResultState.data(MovieListViewState(movieList = cachedList)))
        }
            .onStart { emit(ResultState.loading(true)) }
            .catch { cause -> emit(ResultState.error(cause)) }
            .flowOn(dispatcher)

    }

//    override fun fetchList(id: Int?): Flow<ResultState<MovieListViewState>> {
//        return flow {
//            if (InternetReachability.isInternetAvailable(context)) {
//                val networkResponse = network.listById(id)
//                val movieLists = networkResponse.results.map { it.toData() }
//                updateMovieListInCache(movieLists)
//            } else {
//                emit(ResultState.error<MovieListViewState>(Throwable("No Internet")))
//            }
//            emit(
//                ResultState.data(
//                    MovieListViewState(
//                        movieList = cache.getAll().map { it.toEntity() })
//                )
//            )
//        }
//            .onStart {
//                emit(ResultState.loading(true))
//            }
//            .catch { cause ->
//                emit(ResultState.error(cause))
//            }
//            .flowOn(dispatcher)
//    }
//    private fun updateMovieListInCache(networkList: List<MovieData>?) {
//        networkList?.let { list ->
//            Log.d(javaClass.simpleName, "Updating Cache: $list")
//            cache.insertAll(*list.toTypedArray())
//        }
//    }

    override fun queryList(query: String): Flow<ResultState<MovieListViewState>> {
        return cache.selectByTitle(query).mapLatest { movies ->
            ResultState.data(MovieListViewState(movieList = movies.map { it.toEntity() }))
        }
            .onStart {
                emit(ResultState.loading(true))
            }
            .catch { cause ->
                emit(ResultState.error(cause))
            }
            .flowOn(dispatcher)
    }

    override fun fetchDetail(id: Int?): Flow<ResultState<MovieDetailViewState>> {
        return flow {
            val detail = network.movieDetail(id)
            val state = ResultState.data(MovieDetailViewState(movieDetail = detail))
            emit(state)
        }
            .onStart {
                emit(ResultState.loading(true))
            }
            .catch { cause ->
                emit(ResultState.error(cause))
            }
            .flowOn(dispatcher)
    }
}