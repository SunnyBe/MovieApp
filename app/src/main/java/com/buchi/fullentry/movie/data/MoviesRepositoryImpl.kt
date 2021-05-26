package com.buchi.fullentry.movie.data

import android.content.Context
import com.buchi.fullentry.movie.data.cache.MovieDao

class MoviesRepositoryImpl(val context: Context, val network: MovieService, val cache: MovieDao) : MovieRepository {
}