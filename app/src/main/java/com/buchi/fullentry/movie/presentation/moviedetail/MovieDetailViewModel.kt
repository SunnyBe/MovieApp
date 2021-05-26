package com.buchi.fullentry.movie.presentation.moviedetail

import androidx.lifecycle.ViewModel
import com.buchi.fullentry.movie.data.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

}