package com.buchi.fullentry.movie.data.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieData(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "title")
    val title: String?
)