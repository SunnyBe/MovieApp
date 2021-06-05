package com.buchi.fullentry.cars.presentation.carlist

import com.buchi.fullentry.cars.model.Car

data class CarListViewState(
    val carList: List<Car>? = null
)