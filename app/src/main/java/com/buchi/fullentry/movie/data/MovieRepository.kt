package com.buchi.fullentry.movie.data

import com.buchi.core.utils.ResultState
import com.buchi.fullentry.movie.presentation.moviedetail.MovieDetailViewState
import com.buchi.fullentry.movie.presentation.movielist.MovieListViewState
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    /**
     * Make a request for list of movies. Response from Network call is saved in Cache such that the
     * Cache feeds the consuming class usually[ViewModel] with the list of movies.
     * A prior internet check is done to make sure request can be made over the internet.
     * @param id for the request which is always set to 1
     * @return Updated [MovieListViewModel]
     */
    fun fetchList(id: Int?): Flow<ResultState<MovieListViewState>>

    /**
     * A query to fetch the movie using the specified movie query which is the title in this case.
     * @param query which is the title of the movie to be fetched.
     * @return Updated [MovieListViewModel]
     */
    fun queryList(query: String): Flow<ResultState<MovieListViewState>>

    /**
     * A query to fetch the detail of movie using the specified movie id as parameter
     * @param id for the movie detail to be fetched
     * @return Updated [MovieListViewModel]
     */
    fun fetchDetail(id: Int?): Flow<ResultState<MovieDetailViewState>>
}