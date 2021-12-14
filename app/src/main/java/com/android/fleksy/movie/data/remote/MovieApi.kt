package com.android.fleksy.movie.data.remote

import com.android.fleksy.movie.data.remote.dto.MovieListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("tv/top_rated")
    suspend fun getMovies(@Query("page") page: Int): MovieListDto

    @GET("tv/{tvId}/similar")
    suspend fun getSimilarMovies(@Path("tvId") tvId: Int): MovieListDto
}