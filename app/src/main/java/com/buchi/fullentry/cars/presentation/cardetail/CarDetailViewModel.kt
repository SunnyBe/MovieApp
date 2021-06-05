package com.buchi.fullentry.cars.presentation.cardetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buchi.core.utils.ResultState
import com.buchi.fullentry.cars.data.CarRepository
import com.buchi.fullentry.movie.data.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class CarDetailViewModel @Inject constructor(
    private val carRepository: CarRepository
) : ViewModel() {

    // Event Channel: This is updated to perform an event
    private val _stateEvents = MutableStateFlow<CarDetailStateEvents>(CarDetailStateEvents.Idle)
    private val stateEvents: StateFlow<CarDetailStateEvents> = _stateEvents

    // DataState is the state of date returned with ViewState
    private val _dataState = MutableStateFlow<ResultState<CarDetailViewState>>(ResultState.data(null))
    val dataState: StateFlow<ResultState<CarDetailViewState>> get() = _dataState

    // ViewState holds what will be processed on the screen based on the value of the data state returned.
    private val _viewState: MutableStateFlow<CarDetailViewState> = MutableStateFlow(
        CarDetailViewState()
    )
    val viewState: StateFlow<CarDetailViewState> = _viewState

    init {
        stateEvents.flatMapLatest { events ->
            processEvents(events).mapLatest { resp ->
                _dataState.value = resp
            }
        }.launchIn(viewModelScope)

    }

    private fun processEvents(event: CarDetailStateEvents): Flow<ResultState<CarDetailViewState>> {
        return when (event) {
            is CarDetailStateEvents.Idle -> {
                flow { emit(ResultState.data(CarDetailViewState())) }
            }
            is CarDetailStateEvents.FetchCarDetail -> {
                carRepository.fetchDetail(event.id)
            }
        }
    }

    fun setStateEvent(stateEvent: CarDetailStateEvents) {
        _stateEvents.value = stateEvent
    }

    fun processViewState(viewState: CarDetailViewState) {
        _viewState.value = viewState
    }

}