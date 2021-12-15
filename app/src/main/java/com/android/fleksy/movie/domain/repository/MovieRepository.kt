package com.android.fleksy.movie.domain.repository

import com.android.fleksy.movie.data.remote.dto.MovieDto
import com.android.fleksy.movie.data.remote.dto.MovieListDto

interface MovieRepository {

    suspend fun getMovie(tvId: Int): MovieDto

    suspend fun getMovies(page: Int): MovieListDto

    suspend fun getSimilarMovies(tvId: Int): MovieListDto
}