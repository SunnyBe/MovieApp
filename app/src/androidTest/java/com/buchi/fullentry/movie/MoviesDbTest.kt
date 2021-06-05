package com.buchi.fullentry.movie

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.buchi.fullentry.movie.data.cache.MovieDao
import com.buchi.fullentry.movie.data.cache.MoviesAppDatabase
import com.buchi.fullentry.utilities.MockUtilities
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class MoviesDbTest {
    private lateinit var movieDao: MovieDao
    private lateinit var db: MoviesAppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, MoviesAppDatabase::class.java
        ).build()
        movieDao = db.moviesDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInList() {
        runBlocking {
            val movie = MockUtilities.testMovieData
            movieDao.insertAll(movie)
            val byName = movieDao.findByTitle("Harry Porter")
            assertThat(byName, equalTo(movie))
        }
    }

}