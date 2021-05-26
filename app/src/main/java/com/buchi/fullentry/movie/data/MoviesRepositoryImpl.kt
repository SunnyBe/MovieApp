package com.buchi.fullentry.movie.data

import android.content.Context
import com.buchi.fullentry.movie.data.cache.MovieDao

class MoviesRepositoryImpl(val context: Context, val network: MovieService, val cache: MovieDao) : MovieRepository {

    override fun testRepo(): String? {
        return "This is to confirm Repo is usable"
    }
}