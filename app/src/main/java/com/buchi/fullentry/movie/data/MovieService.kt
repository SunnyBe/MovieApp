package com.buchi.fullentry.movie.data

import com.buchi.fullentry.BuildConfig
import com.buchi.fullentry.movie.model.Movie
import com.buchi.fullentry.movie.model.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieService {

    @GET("list/{listId}")
    suspend fun listById(
        @Path(value = "listId") id: Int?,
        @Query(value = "api_key") apiKey: String? = BuildConfig.MOVIE_API_KEY
    ): MovieListResponse

    @GET("")
    suspend fun movidDetail(id: Int?): Movie
}