package com.buchi.fullentry.car

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.buchi.core.utils.ResultState
import com.buchi.fullentry.cars.data.CarRepository
import com.buchi.fullentry.cars.presentation.carlist.CarListStateEvents
import com.buchi.fullentry.cars.presentation.carlist.CarListViewModel
import com.buchi.fullentry.cars.presentation.carlist.CarListViewState
import com.buchi.fullentry.utilities.MainCoroutineScopeRule
import com.buchi.fullentry.utilities.MockUtilities
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import org.junit.*
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class CarListViewModelTest {

    private lateinit var viewModel: CarListViewModel

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
        viewModel = CarListViewModel(carRepo)
    }

    @After
    fun cleanUp() {
        coroutineScope.cleanupTestCoroutines()
        Dispatchers.resetMain()
    }

    @Test
    fun fetchCarListReturnsValidMovieList_updates_viewState_withValidData() {
        coroutineScope.dispatcher.runBlockingTest {
            val expectedFlow = flowOf(
                ResultState.data(CarListViewState(carList = MockUtilities.testCarList(0, 1, 2, 3)))
            )
            Mockito.`when`(carRepo.fetchList()).thenReturn(expectedFlow)

            viewModel.setStateEvent(CarListStateEvents.FetchCarList)

            println(viewModel.dataState.value.data!!.peekContent())
            Assert.assertNotNull(viewModel.dataState.value.data!!.peekContent())
            Assert.assertEquals(
                "pdUCkqhr0",
                viewModel.dataState.value.data?.getContentIfNotHandled()?.carList?.first()?.id
            )
        }
    }

    @Test
    fun fetchMovieListReturnsValidNetworkError_updates_dataState_withError() {
        coroutineScope.dispatcher.runBlockingTest {
            val expectedFlow =
                flowOf(ResultState.error<CarListViewState>(Throwable("Failed to fetch")))
            Mockito.`when`(carRepo.fetchList()).thenReturn(expectedFlow)
            viewModel.setStateEvent(CarListStateEvents.FetchCarList)

            println(viewModel.dataState.value.error!!.peekContent())
            Assert.assertNotNull(viewModel.dataState.value.error!!.peekContent())
            Assert.assertEquals(
                "Failed to fetch",
                viewModel.dataState.value.error?.getContentIfNotHandled()?.message
            )
        }
    }

    @Test(expected = Throwable::class)
    fun fetchMovieListReturnsSystemError() {
        coroutineScope.dispatcher.runBlockingTest {
            Mockito.`when`(carRepo.fetchList()).thenThrow(Throwable("Failed to fetch"))
            viewModel.setStateEvent(CarListStateEvents.FetchCarList)

            println(viewModel.dataState.value.error!!.peekContent())
            Assert.assertNotNull(viewModel.dataState.value.error!!.peekContent())
            Assert.assertEquals(
                "Failed to fetch",
                viewModel.dataState.value.error?.getContentIfNotHandled()?.message
            )
        }
    }

}