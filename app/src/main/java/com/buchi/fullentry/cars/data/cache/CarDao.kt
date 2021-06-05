package com.buchi.fullentry.cars.data.cache

import androidx.room.*
import com.buchi.core.base.BaseDao
import kotlinx.coroutines.flow.Flow

@Dao
interface CarDao: BaseDao {

    @Query("SELECT * FROM cars")
    fun getAll(): List<CarData>

    @Query("SELECT * FROM cars WHERE id IN (:movieIds)")
    fun loadAllByIds(movieIds: IntArray): List<CarData>

    @Query("SELECT * FROM cars WHERE title LIKE :title LIMIT 1")
    fun findByTitle(title: String): CarData

    @Query("SELECT * FROM cars WHERE title LIKE '%' || :title || '%'")
    fun selectByTitle(title: String): Flow<List<CarData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg cars: CarData)

    @Delete
    fun delete(movie: CarData)
}