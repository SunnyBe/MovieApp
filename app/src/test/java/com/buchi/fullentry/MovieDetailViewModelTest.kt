package com.buchi.fullentry

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.buchi.core.utils.ResultState
import com.buchi.fullentry.fakes.MockMovieRepository
import com.buchi.fullentry.movie.data.MovieRepository
import com.buchi.fullentry.movie.data.MoviesRepositoryImpl
import com.buchi.fullentry.movie.presentation.moviedetail.MovieDetailStateEvents
import com.buchi.fullentry.movie.presentation.moviedetail.MovieDetailViewModel
import com.buchi.fullentry.movie.presentation.movielist.MovieListStateEvents
import com.buchi.fullentry.movie.presentation.movielist.MovieListViewModel
import com.buchi.fullentry.utilities.MainCoroutineScopeRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.*
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class MovieDetailViewModelTest {

    private lateinit var viewModel: MovieDetailViewModel

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    @Mock
    lateinit var movieRepo: MovieRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        movieRepo = MockMovieRepository
        viewModel = MovieDetailViewModel(movieRepo)
    }

    @After
    fun cleanUp() {
        coroutineScope.cleanupTestCoroutines()
    }

    @Test
    fun testMovieListViewModel_fetchMovieEvent_updates_viewState_withData() {
        runBlockingTest {
            viewModel.setStateEvent(MovieDetailStateEvents.FetchMovieDetail(1))

            // Test confirmation
            Assert.assertNotNull(viewModel.dataState.value.data!!.peekContent())
            println(viewModel.dataState.value.data!!.peekContent())
            Assert.assertEquals(1, viewModel.dataState.value.data?.getContentIfNotHandled()?.movieDetail?.id)
        }
    }

}