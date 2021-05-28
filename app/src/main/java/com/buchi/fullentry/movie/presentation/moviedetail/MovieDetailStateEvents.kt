package com.buchi.fullentry.movie.presentation.moviedetail

/**
 * StateEvents which denotes possible events that can be triggered from the MovieDetail view class and viewModel. This usually triggers an action
 * through usecases or repository depending on which is injected into the viewModel.
 */
sealed class MovieDetailStateEvents {
    class FetchMovieDetail(val id: Int) : MovieDetailStateEvents()
    object Idle : MovieDetailStateEvents()
}