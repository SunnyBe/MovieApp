package com.buchi.fullentry.movie.data

import android.content.Context
import android.util.Log
import com.buchi.core.utils.InternetReachability
import com.buchi.core.utils.ResultState
import com.buchi.fullentry.movie.data.cache.MovieDao
import com.buchi.fullentry.movie.data.cache.MovieData
import com.buchi.fullentry.movie.domain.toData
import com.buchi.fullentry.movie.domain.toEntity
import com.buchi.fullentry.movie.presentation.moviedetail.MovieDetailViewState
import com.buchi.fullentry.movie.presentation.movielist.MovieListViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MoviesRepositoryImpl @Inject constructor(
    private val context: Context,
    private val network: MovieService,
    private val cache: MovieDao,
    private val dispatcher: CoroutineContext = Dispatchers.IO
) : MovieRepository {
    override fun fetchList(id: Int?): Flow<ResultState<MovieListViewState>> {
        return flow {
            if (InternetReachability.isInternetAvailable(context)) {
                val networkResponse = network.listById(id)
                val movieLists = networkResponse.results.map { it.toData() }
                updateMovieListInCache(movieLists)
            } else {
                emit(ResultState.error<MovieListViewState>(Throwable("No Internet")))
            }
            emit(
                ResultState.data(
                    MovieListViewState(
                        movieList = cache.getAll().map { it.toEntity() })
                )
            )
        }
            .onStart {
                emit(ResultState.loading(true))
            }
            .catch { cause ->
                emit(ResultState.error(cause))
            }
            .flowOn(dispatcher)
    }

    override fun queryList(query: String): Flow<ResultState<MovieListViewState>> {
        return flow {
            val movie = cache.findByTitle(query)
            if (movie != null) {
                emit(ResultState.data(MovieListViewState(movieList = listOf(movie.toEntity()))))
            } else {
                emit(
                    ResultState.data(
                        MovieListViewState(
                            movieList = cache.getAll().map { it.toEntity() })
                    )
                )
            }
        }
            .onStart {
                emit(ResultState.loading(true))
            }
            .catch { cause ->
                emit(ResultState.error(cause))
            }
            .flowOn(dispatcher)
    }

    private fun updateMovieListInCache(networkList: List<MovieData>?) {
        networkList?.let { list ->
            Log.d(javaClass.simpleName, "Updating Cache: $list")
            cache.insertAll(*list.toTypedArray())
        }
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