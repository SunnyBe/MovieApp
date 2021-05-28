package com.buchi.fullentry.movie.presentation

import androidx.lifecycle.ViewModel
import com.buchi.core.utils.ResultState
import com.buchi.fullentry.movie.presentation.movielist.MovieListViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(): ViewModel() {

    private val _loading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loading: StateFlow<Boolean> get() = _loading

    private val _error: MutableStateFlow<String?> = MutableStateFlow(null)
    val error: StateFlow<String?> get() = _error

    fun dataStateChanged(dataState: ResultState<*>) {
        _error.value = dataState.error?.peekContent()?.message
        _loading.value = dataState.loading
    }

}