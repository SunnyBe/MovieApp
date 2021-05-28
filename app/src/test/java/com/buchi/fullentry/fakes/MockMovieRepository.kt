package com.buchi.fullentry.fakes

import com.buchi.core.utils.ResultState
import com.buchi.fullentry.MockUtilities
import com.buchi.fullentry.movie.data.MovieRepository
import com.buchi.fullentry.movie.presentation.moviedetail.MovieDetailViewState
import com.buchi.fullentry.movie.presentation.movielist.MovieListViewState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object MockMovieRepository: MovieRepository {

    override fun fetchList(id: Int?): Flow<ResultState<MovieListViewState>> = flow {
        val movieList = MockUtilities.testMovieList(0, 1, 2, 3, 4, 5)
        emit(ResultState.data(MovieListViewState(movieList = movieList)))
    }

    override fun fetchDetail(id: Int?): Flow<ResultState<MovieDetailViewState>> = flow {
        emit(ResultState.data(MovieDetailViewState(movieDetail = MockUtilities.testMovie(1))))
    }

    override fun queryList(query: String): Flow<ResultState<MovieListViewState>> = flow {
        val movieList = MockUtilities.testMovieList(0, 1, 2, 3, 4, 5)
        emit(ResultState.data(MovieListViewState(movieList = movieList)))
    }
}