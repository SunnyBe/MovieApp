package com.buchi.fullentry.movie.presentation.movielist

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
class MovieListViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    val listId: Int = 1

    // Event Channel: This is updated to perform an event
    private val _stateEvents = MutableStateFlow<MovieListStateEvents>(MovieListStateEvents.Idle)
    private val stateEvents: StateFlow<MovieListStateEvents> = _stateEvents

    // DataState is the state of date returned with ViewState
    private val _dataState =
        MutableStateFlow<ResultState<MovieListViewState>>(ResultState.data(null))
    val dataState: StateFlow<ResultState<MovieListViewState>> get() = _dataState

    // ViewState holds what will be processed on the screen based on the value of the data state returned.
    private val _viewState: MutableStateFlow<MovieListViewState> =
        MutableStateFlow(MovieListViewState())
    val viewState: StateFlow<MovieListViewState> = _viewState

    init {
        stateEvents.flatMapLatest { events ->
            processEvents(events).mapLatest { resp ->
                _dataState.value = resp
            }
        }
            .launchIn(viewModelScope)
    }

    private fun processEvents(event: MovieListStateEvents): Flow<ResultState<MovieListViewState>> {
        return when (event) {
            is MovieListStateEvents.Idle -> {
                flow { emit(ResultState.data(MovieListViewState())) }
            }
            is MovieListStateEvents.FetchMovieList -> {
                movieRepository.fetchList(event.id)
            }

            is MovieListStateEvents.QueryList -> {
                movieRepository.queryList(event.query)
            }
        }
    }

    fun setStateEvent(stateEvent: MovieListStateEvents) {
        _stateEvents.value = stateEvent
    }

    fun processViewState(viewState: MovieListViewState) {
        _viewState.value = viewState
    }
}