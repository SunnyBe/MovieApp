package com.buchi.fullentry.cars.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [
    CarData::class
], version = 1, exportSchema = false)
abstract class CarAppDatabase: RoomDatabase() {
    abstract fun carsDao(): CarDao
}