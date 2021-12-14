package com.android.fleksy.movie.domain.repository

import com.android.fleksy.movie.data.remote.dto.MovieDto

interface MovieRepository {

    suspend fun getMovies(): MovieDto

    suspend fun getSimilarMovies(tvId: Int): MovieDto
}