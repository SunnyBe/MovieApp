package com.buchi.fullentry.movie.data.cache

import androidx.room.*
import com.buchi.core.base.BaseDao
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao: BaseDao {

    @Query("SELECT * FROM movie")
    fun getAll(): List<MovieData>

    @Query("SELECT * FROM movie WHERE id IN (:movieIds)")
    fun loadAllByIds(movieIds: IntArray): List<MovieData>

    @Query("SELECT * FROM movie WHERE title LIKE :title LIMIT 1")
    fun findByTitle(title: String): MovieData

    @Query("SELECT * FROM movie WHERE title LIKE '%' || :title || '%'")
    fun selectByTitle(title: String): Flow<List<MovieData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg movies: MovieData)

    @Delete
    fun delete(movie: MovieData)
}