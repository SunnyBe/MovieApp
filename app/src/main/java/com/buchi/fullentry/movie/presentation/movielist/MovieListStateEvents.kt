package com.buchi.fullentry.movie.presentation.movielist

/**
 * StateEvents which denotes possible events that can be triggered from the MovieList view and viewModel. This usually triggers an action
 * through usecases or repository depending on which is injected into the viewModel
 */
sealed class MovieListStateEvents {
    data class FetchMovieList(val id: Int?) : MovieListStateEvents()
    data class QueryList(val query: String) : MovieListStateEvents()
    object Idle : MovieListStateEvents()
}