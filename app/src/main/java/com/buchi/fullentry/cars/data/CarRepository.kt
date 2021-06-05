package com.buchi.fullentry.cars.data

import com.buchi.core.utils.ResultState
import com.buchi.fullentry.cars.model.CarMake
import com.buchi.fullentry.cars.presentation.cardetail.CarDetailViewState
import com.buchi.fullentry.cars.presentation.carlist.CarListViewState
import kotlinx.coroutines.flow.Flow

interface CarRepository {
    /**
     * Make a request for list of cars. Response from Network call is saved in Cache such that the
     * Cache feeds the consuming class usually[ViewModel] with the list of cars.
     * A prior internet check is done to make sure request can be made over the internet.
     * @return Updated [CarListViewState]
     */
    fun fetchList(): Flow<ResultState<CarListViewState>>

    /**
     * A query to fetch the car using the specified car query which is the title in this case.
     * @param query which is the title of the car to be fetched.
     * @return Updated [CarListViewState]
     */
    fun queryList(query: String): Flow<ResultState<CarListViewState>>

    /**
     * A query to fetch the detail of car using the specified car id as parameter
     * @param id for the car detail to be fetched
     * @return Updated [CarListViewState]
     */
    fun fetchDetail(id: String?): Flow<ResultState<CarDetailViewState>>
    
    fun fetchCarMakes(): Flow<ResultState<List<CarMake>>>
}