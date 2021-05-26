package com.buchi.fullentry.movie.presentation.moviedetail

sealed class MovieDetailStateEvents
object FetchMovieDetail : MovieDetailStateEvents()
object Idle : MovieDetailStateEvents()