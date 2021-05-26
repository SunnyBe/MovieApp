package com.buchi.fullentry.movie.presentation.movielist

import androidx.lifecycle.ViewModel
import com.buchi.fullentry.movie.data.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    fun testRepo(): String? {
        return movieRepository.testRepo()
    }
}