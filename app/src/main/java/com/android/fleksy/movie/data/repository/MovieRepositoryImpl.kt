package com.android.fleksy.movie.data.repository

import com.android.fleksy.movie.data.remote.MovieApi
import com.android.fleksy.movie.data.remote.dto.MovieListDto
import com.android.fleksy.movie.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi
): MovieRepository {

    override suspend fun getMovies(): MovieListDto = api.getMovies()

    override suspend fun getSimilarMovies(tvId: Int): MovieListDto = api.getSimilarMovies(tvId)
}