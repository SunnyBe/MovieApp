package com.buchi.fullentry.movie.model

import com.google.gson.annotations.SerializedName

data class Movie(
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

/*

{
      "adult": false,
      "backdrop_path": "/AqLcLsGGTzAjm3pCCq0CZCQrp6m.jpg",
      "genre_ids": [
        12,
        14
      ],
      "id": 12444,
      "media_type": "movie",
      "original_language": "en",
      "original_title": "Harry Potter and the Deathly Hallows: Part 1",
      "overview": "Harry, Ron and Hermione walk away from their last year at Hogwarts to find and destroy the remaining Horcruxes, putting an end to Voldemort's bid for immortality. But with Harry's beloved Dumbledore dead and Voldemort's unscrupulous Death Eaters on the loose, the world is more dangerous than ever.",
      "popularity": 100.048,
      "poster_path": "/iGoXIpQb7Pot00EEdwpwPajheZ5.jpg",
      "release_date": "2010-10-17",
      "title": "Harry Potter and the Deathly Hallows: Part 1",
      "video": false,
      "vote_average": 7.8,
      "vote_count": 14566
    },
 */