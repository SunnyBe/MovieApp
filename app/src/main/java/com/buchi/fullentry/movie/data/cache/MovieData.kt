package com.buchi.fullentry.movie.data.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieData(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "title")
    val title: String?,
    @ColumnInfo(name = "original_title")
    val originalTitle: String?,
    @ColumnInfo(name = "original_language")
    val originalLanguage: String?,
    @ColumnInfo(name = "adult")
    val adult: Boolean?,
    @ColumnInfo(name = "overview")
    val overview: String?,
    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String?,
    @ColumnInfo(name = "genre_ids")
    val genreIds: String?,  // Todo change back to List of Int with converter
    @ColumnInfo(name = "media_type")
    val mediaType: String?,
    @ColumnInfo(name = "popularity")
    val popularity: Double?,
    @ColumnInfo(name = "poster_path")
    val posterPath: String?,
    @ColumnInfo(name = "vote_average")
    val voteAverage: String?,
    @ColumnInfo(name = "vote_count")
    val voteCount: Int?,
    @ColumnInfo(name = "release_date")
    val releaseDate: String?
)