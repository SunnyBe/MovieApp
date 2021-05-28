package com.buchi.fullentry.movie.domain

import com.buchi.fullentry.movie.data.cache.MovieData
import com.buchi.fullentry.movie.data.network.MovieDto

fun MovieDto.toData() = MovieData(
    id, title, originalTitle, originalLanguage, adult, overview, backdropPath, genreIds.toString(), mediaType, popularity, posterPath, voteAverage, voteCount, releaseDate
)