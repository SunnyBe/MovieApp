package com.buchi.fullentry

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.buchi.core.utils.ResultState
import com.buchi.fullentry.fakes.MockMovieRepository
import com.buchi.fullentry.movie.data.MovieRepository
import com.buchi.fullentry.movie.presentation.movielist.MovieListStateEvents
import com.buchi.fullentry.movie.presentation.movielist.MovieListViewModel
import com.buchi.fullentry.movie.presentation.movielist.MovieListViewState
import com.buchi.fullentry.utilities.MainCoroutineScopeRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import org.junit.*
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import kotlin.jvm.Throws

@ExperimentalCoroutinesApi
class MovieListViewModelTest {

    private lateinit var viewModel: MovieListViewModel

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
        viewModel = MovieListViewModel(movieRepo)
    }

    @After
    fun cleanUp() {
        coroutineScope.cleanupTestCoroutines()
        Dispatchers.resetMain()
    }

    @Test
    fun fetchMovieListReturnsValidMovieList_updates_viewState_withValidData() {
        coroutineScope.dispatcher.runBlockingTest {
            val expectedFlow = flowOf(
                ResultState.data(
                    null,
                    MovieListViewState(movieList = MockUtilities.testMovieList(1, 2, 3, 4))
                )
            )
            Mockito.`when`(movieRepo.fetchList(1)).thenReturn(expectedFlow)
            viewModel.setStateEvent(MovieListStateEvents.FetchMovieList(1))

            println(viewModel.dataState.value.data!!.peekContent())
            Assert.assertNotNull(viewModel.dataState.value.data!!.peekContent())
            Assert.assertEquals(1, viewModel.dataState.value.data?.getContentIfNotHandled()?.movieList?.first()?.id)
        }
    }

    @Test
    fun fetchMovieListReturnsValidNetworkError_updates_dataState_withError() {
        coroutineScope.dispatcher.runBlockingTest {
            val expectedFlow = flowOf(ResultState.error<MovieListViewState>( "Failed to fetch", Throwable("Failed to fetch")))
            Mockito.`when`(movieRepo.fetchList(1)).thenReturn(expectedFlow)
            viewModel.setStateEvent(MovieListStateEvents.FetchMovieList(1))

            println(viewModel.dataState.value.error!!.peekContent())
            Assert.assertNotNull(viewModel.dataState.value.error!!.peekContent())
            Assert.assertEquals("Failed to fetch", viewModel.dataState.value.error?.getContentIfNotHandled()?.message)
        }
    }

    @Test(expected = Throwable::class)
    fun fetchMovieListReturnsSystemError() {
        coroutineScope.dispatcher.runBlockingTest {
            Mockito.`when`(movieRepo.fetchList(1)).thenThrow(Throwable("Failed to fetch"))
            viewModel.setStateEvent(MovieListStateEvents.FetchMovieList(1))

            println(viewModel.dataState.value.error!!.peekContent())
            Assert.assertNotNull(viewModel.dataState.value.error!!.peekContent())
            Assert.assertEquals("Failed to fetch", viewModel.dataState.value.error?.getContentIfNotHandled()?.message)
        }
    }

}