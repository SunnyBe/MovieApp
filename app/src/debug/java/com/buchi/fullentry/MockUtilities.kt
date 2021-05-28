package com.buchi.fullentry

import com.buchi.fullentry.movie.data.cache.MovieData
import com.buchi.fullentry.movie.model.Movie

object MockUtilities {
    fun testMovieList(vararg ids: Int): List<Movie> {
        return mutableListOf<Movie>().apply {
            ids.forEach { id ->
                add(testMovie(id))
            }
        }
    }

    fun testMovie(id: Int): Movie = Movie(
        id = id,
        title = "Test title $id",
        originalTitle = "Original test title $id",
        adult = false,
        originalLanguage = "EN",
        overview = "Test Overview $id",
        backdropPath = "test/backdroppath/$id/com",
        genreIds = listOf(0, 1),
        mediaType = "TestMedia:$id",
        popularity = id * Math.random(),
        posterPath = "test/posterPath/$id/com",
        voteAverage = "$id",
        voteCount = id * Math.random().toInt(),
        releaseDate = "2020-19-02"
    )

    val testMovieData = MovieData(
        id = 0,
        title = "Harray Porter",
        originalTitle = "Original test title",
        adult = false,
        originalLanguage = "EN",
        overview = "Test Overview",
        backdropPath = "test/backdroppath/0/com",
        genreIds = "1,2",
        mediaType = "TestMedia",
        popularity = Math.random(),
        posterPath = "test/posterPath/0/com",
        voteAverage = "0",
        voteCount = Math.random().toInt(),
        releaseDate = "2020-19-02"
    )
}