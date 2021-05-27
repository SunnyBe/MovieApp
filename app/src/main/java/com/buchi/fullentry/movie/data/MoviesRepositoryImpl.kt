package com.buchi.fullentry.movie.data

import android.content.Context
import android.util.Log
import com.buchi.core.utils.ResultState
import com.buchi.fullentry.movie.data.cache.MovieDao
import com.buchi.fullentry.movie.presentation.moviedetail.MovieDetailViewState
import com.buchi.fullentry.movie.presentation.movielist.MovieListViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class MoviesRepositoryImpl(
    val context: Context,
    private val network: MovieService,
    val cache: MovieDao
) : MovieRepository {

    override fun fetchList(id: Int?): Flow<ResultState<MovieListViewState>> {
        return flow {
            Log.d(javaClass.simpleName, "Repo to fetch movie list")
            val listResponse = network.listById(id)
            val state = ResultState.data(null, MovieListViewState(movieList = listResponse.results))
            emit(state)
        }
            .onStart {
                emit(ResultState.loading(true))
            }
            .catch { cause ->
                emit(ResultState.error("Failed request", cause))
            }
            .flowOn(Dispatchers.IO)
    }

    override fun fetchDetail(id: Int?): Flow<ResultState<MovieDetailViewState>> {
        return flow {
            Log.d(javaClass.simpleName, "Repo to fetch movie list")
            val listResponse = network.movidDetail(id)
            val state = ResultState.data(null, MovieDetailViewState(movieDetail = null))
            emit(state)
        }
            .onStart {
                emit(ResultState.loading(true))
            }
            .catch { cause ->
                emit(ResultState.error("Failed request", cause))
            }
            .flowOn(Dispatchers.IO)
    }
}