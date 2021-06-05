package com.buchi.fullentry.movie

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.buchi.fullentry.movie.data.MovieRepository
import com.buchi.fullentry.movie.data.MovieService
import com.buchi.fullentry.movie.data.MoviesRepositoryImpl
import com.buchi.fullentry.movie.data.cache.MoviesAppDatabase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class MovieNetworkTest {
    lateinit var movieRepository: MovieRepository
    lateinit var network: MovieService

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
    }

    @After
    @Throws(IOException::class)
    fun cleanUp() {
    }

    @Test
    fun fetchListReturnsValidMovieList() {

    }

}