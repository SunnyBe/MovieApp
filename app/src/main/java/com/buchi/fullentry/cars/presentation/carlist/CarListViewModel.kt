package com.buchi.fullentry.cars.presentation.carlist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buchi.core.utils.ResultState
import com.buchi.fullentry.cars.data.CarRepository
import com.buchi.fullentry.cars.model.CarMake
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class CarListViewModel @Inject constructor(
    private val carRepository: CarRepository
) : ViewModel() {

    val listId: Int = 1

    // Event Channel: This is updated to perform an event
    private val _stateEvents = MutableStateFlow<CarListStateEvents>(CarListStateEvents.Idle)
    private val stateEvents: SharedFlow<CarListStateEvents> get() = _stateEvents

    // DataState is the state of date returned with ViewState
    private val _dataState =
        MutableStateFlow<ResultState<CarListViewState>>(ResultState.data(null))
    val dataState: StateFlow<ResultState<CarListViewState>> get() = _dataState

    // ViewState holds what will be processed on the screen based on the value of the data state returned.
    private val _viewState: MutableStateFlow<CarListViewState> =
        MutableStateFlow(CarListViewState())
    val viewState: StateFlow<CarListViewState> = _viewState

    val carMake: Flow<ResultState<List<CarMake>>> = carRepository.fetchCarMakes()

    init {
        stateEvents.flatMapLatest { events ->
            processEvents(events).onEach { resp ->
                _dataState.value = resp
            }
        }
            .launchIn(viewModelScope)
    }

    private fun processEvents(event: CarListStateEvents): Flow<ResultState<CarListViewState>> {
        return when (event) {
            is CarListStateEvents.Idle -> {
                flow { emit(ResultState.data(CarListViewState())) }
            }
            is CarListStateEvents.FetchCarList -> {
                carRepository.fetchList()
            }

            is CarListStateEvents.QueryList -> {
                carRepository.queryList(event.query)
            }
        }
    }

    fun setStateEvent(stateEvent: CarListStateEvents) {
        _stateEvents.value = stateEvent
    }

    fun processViewState(viewState: CarListViewState) {
        _viewState.value = viewState
    }
}