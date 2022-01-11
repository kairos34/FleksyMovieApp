package com.android.fleksy.movie.data.remote

import com.android.fleksy.movie.data.remote.dto.MovieDto
import com.android.fleksy.movie.data.remote.dto.MovieListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/{movieId}")
    suspend fun getMovie(@Path("movieId") movieId: Int): MovieDto

    @GET("movie/top_rated")
    suspend fun getMovies(@Query("page") page: Int): MovieListDto

    @GET("movie/{movieId}/similar")
    suspend fun getSimilarMovies(@Path("movieId") movieId: Int): MovieListDto
}