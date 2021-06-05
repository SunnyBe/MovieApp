package com.buchi.fullentry.cars.presentation.cardetail

/**
 * StateEvents which denotes possible events that can be triggered from the CarDetail view class and viewModel. This usually triggers an action
 * through UseCases or repository depending on which is injected into the viewModel.
 */
sealed class CarDetailStateEvents {
    class FetchCarDetail(val id: String?) : CarDetailStateEvents()
    object Idle : CarDetailStateEvents()
}