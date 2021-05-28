package com.buchi.fullentry.movie.data.network

import com.google.gson.annotations.SerializedName

data class MovieDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    @SerializedName("media_type")
    val mediaType: String,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("vote_average")
    val voteAverage: String,
    @SerializedName("vote_count")
    val voteCount: Int,
    @SerializedName("release_date")
    val releaseDate: String
)