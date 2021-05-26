package com.buchi.fullentry.movie.presentation.movielist

sealed class MovieListStateEvents
data class FetchMovieList(val id: Int?): MovieListStateEvents()
object Idle: MovieListStateEvents()