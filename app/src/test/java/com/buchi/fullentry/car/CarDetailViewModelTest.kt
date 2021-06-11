package com.buchi.fullentry.car

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.buchi.core.utils.ResultState
import com.buchi.fullentry.cars.data.CarRepository
import com.buchi.fullentry.cars.presentation.cardetail.CarDetailStateEvents
import com.buchi.fullentry.cars.presentation.cardetail.CarDetailViewModel
import com.buchi.fullentry.cars.presentation.cardetail.CarDetailViewState
import com.buchi.fullentry.utilities.MainCoroutineScopeRule
import com.buchi.fullentry.utilities.MockUtilities
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.*
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class CarDetailViewModelTest {

    private lateinit var viewModel: CarDetailViewModel

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    private val carRepo: CarRepository by lazy {
        Mockito.mock(CarRepository::class.java)
    }

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = CarDetailViewModel(carRepo)
    }

    @After
    fun cleanUp() {
        coroutineScope.cleanupTestCoroutines()
    }


    @Test
    fun fetchMovieDetailReturnsValidMovieDetail_updates_viewState_withValidData() {
        coroutineScope.dispatcher.runBlockingTest {
            val expectedFlow = flowOf(
                ResultState.data(
                    CarDetailViewState(carDetail = MockUtilities.testCar(1))
                )
            )
            Mockito.`when`(carRepo.fetchDetail("pdUCkqhr1")).thenReturn(expectedFlow)
            viewModel.setStateEvent(CarDetailStateEvents.FetchCarDetail("pdUCkqhr1"))

            Assert.assertNotNull(viewModel.dataState.value.data!!.peekContent())
            println(viewModel.dataState.value.data!!.peekContent())
            Assert.assertEquals(
                "pdUCkqhr1",
                viewModel.dataState.value.data?.getContentIfNotHandled()?.carDetail?.id
            )
        }
    }

    @Test
    fun fetchMovieListReturnsValidNetworkError_updates_dataState_withError() {
        coroutineScope.dispatcher.runBlockingTest {
            val expectedFlow = flowOf(
                ResultState.error<CarDetailViewState>(
                    Throwable("Failed to fetch")
                )
            )
            Mockito.`when`(carRepo.fetchDetail("pdUCkqhr1")).thenReturn(expectedFlow)
            viewModel.setStateEvent(CarDetailStateEvents.FetchCarDetail("pdUCkqhr1"))

            println(viewModel.dataState.value.error!!.peekContent())
            Assert.assertNotNull(viewModel.dataState.value.error!!.peekContent())
            Assert.assertEquals(
                "Failed to fetch",
                viewModel.dataState.value.error?.getContentIfNotHandled()?.message
            )
        }
    }
}

