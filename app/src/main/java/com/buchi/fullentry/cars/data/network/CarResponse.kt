package com.buchi.fullentry.cars.data.network

import com.buchi.fullentry.cars.model.Car
import com.buchi.fullentry.cars.model.CarMake

data class CarResponse(
    val result: List<Car>?,
    val makeList: List<CarMake>?
)