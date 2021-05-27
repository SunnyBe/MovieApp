package com.buchi.fullentry.movie.presentation.moviedetail

sealed class MovieDetailStateEvents {
    class FetchMovieDetail(val id: Int) : MovieDetailStateEvents()
    object Idle : MovieDetailStateEvents()
}