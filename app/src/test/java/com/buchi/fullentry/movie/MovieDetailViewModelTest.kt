package com.buchi.fullentry.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.buchi.core.utils.ResultState
import com.buchi.fullentry.movie.data.MovieRepository
import com.buchi.fullentry.movie.presentation.moviedetail.MovieDetailStateEvents
import com.buchi.fullentry.movie.presentation.moviedetail.MovieDetailViewModel
import com.buchi.fullentry.movie.presentation.moviedetail.MovieDetailViewState
import com.buchi.fullentry.utilities.MainCoroutineScopeRule
import com.buchi.fullentry.utilities.MockUtilities
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.*
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class MovieDetailViewModelTest {

    private lateinit var viewModel: MovieDetailViewModel

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    private val movieRepo: MovieRepository by lazy {
        Mockito.mock(MovieRepository::class.java)
    }

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = MovieDetailViewModel(movieRepo)
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
                    MovieDetailViewState(movieDetail = MockUtilities.testMovie(1))
                )
            )
            Mockito.`when`(movieRepo.fetchDetail(1)).thenReturn(expectedFlow)
            viewModel.setStateEvent(MovieDetailStateEvents.FetchMovieDetail(1))

            Assert.assertNotNull(viewModel.dataState.value.data!!.peekContent())
            println(viewModel.dataState.value.data!!.peekContent())
            Assert.assertEquals(
                1,
                viewModel.dataState.value.data?.getContentIfNotHandled()?.movieDetail?.id
            )
        }
    }

    @Test
    fun fetchMovieListReturnsValidNetworkError_updates_dataState_withError() {
        coroutineScope.dispatcher.runBlockingTest {
            val expectedFlow = flowOf(
                ResultState.error<MovieDetailViewState>(
                    Throwable("Failed to fetch")
                )
            )
            Mockito.`when`(movieRepo.fetchDetail(1)).thenReturn(expectedFlow)
            viewModel.setStateEvent(MovieDetailStateEvents.FetchMovieDetail(1))

            println(viewModel.dataState.value.error!!.peekContent())
            Assert.assertNotNull(viewModel.dataState.value.error!!.peekContent())
            Assert.assertEquals(
                "Failed to fetch",
                viewModel.dataState.value.error?.getContentIfNotHandled()?.message
            )
        }
    }
}

