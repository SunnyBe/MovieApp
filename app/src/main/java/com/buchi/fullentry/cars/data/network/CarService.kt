package com.buchi.fullentry.cars.data.network

import com.buchi.fullentry.cars.model.Car
import retrofit2.http.GET
import retrofit2.http.Path

interface CarService {

    @GET("inventory/car/search")
    suspend fun carsList(): CarResponse
    @GET("inventory/make?popular=true")
    suspend fun carMakes(): CarResponse
    @GET("inventory/car/{id}")
    suspend fun carDetail(@Path(value = "id") id: String?): Car
}