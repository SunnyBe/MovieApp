package com.buchi.fullentry.movie.data.cache

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getAll(): List<MovieData>

    @Query("SELECT * FROM movie WHERE id IN (:movieIds)")
    fun loadAllByIds(movieIds: IntArray): List<MovieData>

    @Query("SELECT * FROM movie WHERE title LIKE :title LIMIT 1")
    fun findByTitle(title: String): MovieData

    @Insert
    fun insertAll(vararg movies: MovieData)

    @Delete
    fun delete(movie: MovieData)
}