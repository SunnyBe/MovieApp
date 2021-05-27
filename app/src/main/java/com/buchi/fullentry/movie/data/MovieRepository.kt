package com.buchi.fullentry.movie.data

import com.buchi.core.utils.ResultState
import com.buchi.fullentry.movie.presentation.moviedetail.MovieDetailViewState
import com.buchi.fullentry.movie.presentation.movielist.MovieListViewState
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun fetchList(id: Int?): Flow<ResultState<MovieListViewState>>
    fun fetchDetail(id: Int?): Flow<ResultState<MovieDetailViewState>>
}