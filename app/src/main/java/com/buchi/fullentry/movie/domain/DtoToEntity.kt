package com.buchi.fullentry.movie.domain

import com.buchi.fullentry.movie.data.network.MovieDto
import com.buchi.fullentry.movie.model.Movie

fun MovieDto.toEntity() = Movie(
    id,
    title,
    originalTitle,
    originalLanguage,
    adult,
    overview,
    backdropPath,
    genreIds,
    mediaType,
    popularity,
    posterPath,
    voteAverage,
    voteCount,
    releaseDate
)