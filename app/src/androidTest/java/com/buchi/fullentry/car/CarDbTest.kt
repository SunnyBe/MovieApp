package com.buchi.fullentry.car

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.buchi.fullentry.cars.data.cache.CarAppDatabase
import com.buchi.fullentry.cars.data.cache.CarDao
import com.buchi.fullentry.utilities.MockUtilities
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class CarDbTest {
    private lateinit var carDao: CarDao
    private lateinit var db: CarAppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, CarAppDatabase::class.java).build()
        carDao = db.carsDao()
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
            val cars = MockUtilities.testCarData
            carDao.insertAll(cars)
            val byName = carDao.findByTitle("Mercedes-Benz G 63 AMG")
            assertThat(byName, equalTo(cars))
        }
    }

}