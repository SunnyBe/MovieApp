package com.buchi.fullentry.movie.model

data class Movie(
    val id: Int,
    val title: String,
    val originalTitle: String,
    val originalLanguage: String,
    val adult: Boolean,
    val overview: String,
    val backdrop_path: String,
    val genreIds: List<Int>,
    val mediaType: String,
    val popularity: Double,
    val posterPath: String,
    val voteAverage: String,
    val voteCount: Int,
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