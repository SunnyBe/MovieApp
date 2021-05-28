package com.buchi.fullentry.movie.presentation.moviedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buchi.core.utils.ResultState
import com.buchi.fullentry.movie.data.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    // Event Channel: This is updated to perform an event
    private val _stateEvents = MutableStateFlow<MovieDetailStateEvents>(MovieDetailStateEvents.Idle)
    private val stateEvents: StateFlow<MovieDetailStateEvents> = _stateEvents

    // DataState is the state of date returned with ViewState
    private val _dataState = MutableStateFlow<ResultState<MovieDetailViewState>>(ResultState.data(null))
    val dataState: StateFlow<ResultState<MovieDetailViewState>> get() = _dataState

    // ViewState holds what will be processed on the screen based on the value of the data state returned.
    private val _viewState: MutableStateFlow<MovieDetailViewState> = MutableStateFlow(
        MovieDetailViewState()
    )
    val viewState: StateFlow<MovieDetailViewState> = _viewState

    init {
        stateEvents.flatMapLatest { events ->
            processEvents(events).mapLatest { resp ->
                _dataState.value = resp
            }
        }.launchIn(viewModelScope)

    }

    private fun processEvents(event: MovieDetailStateEvents): Flow<ResultState<MovieDetailViewState>> {
        return when (event) {
            is MovieDetailStateEvents.Idle -> {
                flow { emit(ResultState.data(MovieDetailViewState())) }
            }
            is MovieDetailStateEvents.FetchMovieDetail -> {
                movieRepository.fetchDetail(event.id)
            }
        }
    }

    fun setStateEvent(stateEvent: MovieDetailStateEvents) {
        _stateEvents.value = stateEvent
    }

    fun processViewState(viewState: MovieDetailViewState) {
        _viewState.value = viewState
    }

}