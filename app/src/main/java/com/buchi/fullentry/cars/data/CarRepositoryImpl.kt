package com.buchi.fullentry.cars.data

import android.content.Context
import com.buchi.core.utils.ResultState
import com.buchi.fullentry.cars.data.cache.CarAppDatabase
import com.buchi.fullentry.cars.data.network.CarService
import com.buchi.fullentry.cars.model.CarMake
import com.buchi.fullentry.cars.presentation.cardetail.CarDetailViewState
import com.buchi.fullentry.cars.presentation.carlist.CarListViewState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

class CarRepositoryImpl(
    val context: Context,
    val network: CarService,
    val cache: CarAppDatabase
) : CarRepository {
    override fun fetchList(): Flow<ResultState<CarListViewState>> = flow {
        val cars = network.carsList()
        emit(ResultState.data(CarListViewState(carList = cars.result)))
    }.onStart { emit(ResultState.loading(true)) }
        .catch { cause->
            cause.printStackTrace()
            emit(ResultState.error(cause, "Failed to fetch list"))
        }

    override fun queryList(query: String): Flow<ResultState<CarListViewState>> {
        TODO("Not yet implemented")
    }

    override fun fetchDetail(id: String?): Flow<ResultState<CarDetailViewState>> = flow{
        val carDetail = network.carDetail(id)
        emit(ResultState.data(CarDetailViewState(carDetail = carDetail)))
    }
        .onStart { emit(ResultState.loading(true)) }
        .catch { cause->
            cause.printStackTrace()
            emit(ResultState.error(cause, "Failed to fetch list"))
        }

    override fun fetchCarMakes(): Flow<ResultState<List<CarMake>>> = flow {
        val carMakes = network.carMakes().makeList
        carMakes?.let { makes->
            emit(ResultState.data(makes))
        }?: kotlin.run {
            throw RuntimeException("Empty result list!")
        }
    }.catch { cause ->
        cause.printStackTrace()
        emit(ResultState.error(cause, "Failed to fetch makes"))
    }

}