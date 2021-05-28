package com.buchi.fullentry.movie.domain

import com.buchi.fullentry.movie.data.cache.MovieData
import com.buchi.fullentry.movie.model.Movie

fun MovieData.toEntity() = Movie(
    id, title, originalTitle, originalLanguage, adult, overview, backdropPath, listOf(1,2), mediaType, popularity, posterPath, voteAverage, voteCount, releaseDate
)