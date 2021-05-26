package com.buchi.fullentry.movie.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [
    MovieData::class
], version = 1, exportSchema = false)
abstract class MoviesAppDatabase: RoomDatabase() {

    abstract fun moviesDao(): MovieDao
}