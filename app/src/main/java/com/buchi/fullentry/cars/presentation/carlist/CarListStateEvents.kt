package com.buchi.fullentry.cars.presentation.carlist

/**
 * StateEvents which denotes possible events that can be triggered from the MovieList view and viewModel. This usually triggers an action
 * through usecases or repository depending on which is injected into the viewModel
 */
sealed class CarListStateEvents {
    object FetchCarList : CarListStateEvents()
    data class QueryList(val query: String) : CarListStateEvents()
    object Idle : CarListStateEvents()
}