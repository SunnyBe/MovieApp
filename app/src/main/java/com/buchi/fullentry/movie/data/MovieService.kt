package com.buchi.fullentry.movie.data

import com.buchi.fullentry.movie.model.MovieListResponse
import retrofit2.http.GET


interface MovieService {

    @GET("")
    suspend fun listById(): MovieListResponse
}