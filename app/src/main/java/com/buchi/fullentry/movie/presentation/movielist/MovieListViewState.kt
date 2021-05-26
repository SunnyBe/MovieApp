package com.buchi.fullentry.movie.presentation.movielist

import com.buchi.fullentry.movie.model.Movie

data class MovieListViewState(
    val movieList: List<Movie>? = null
)