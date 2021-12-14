package com.android.fleksy.movie.domain.usecases

import com.android.fleksy.movie.common.Resource
import com.android.fleksy.movie.data.remote.dto.toMovieDetail
import com.android.fleksy.movie.domain.model.MovieDetail
import com.android.fleksy.movie.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetSimilarMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(tvId: Int): Flow<Resource<List<MovieDetail>>> = flow {
        try {
            emit(Resource.Loading<List<MovieDetail>>())
            val movies = repository.getSimilarMovies(tvId).results.map { it.toMovieDetail() }
            emit(Resource.Success<List<MovieDetail>>(movies))
        } catch (e: HttpException) {
            emit(
                Resource.Error<List<MovieDetail>>(
                    e.localizedMessage ?: "An unexpected error occured"
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error<List<MovieDetail>>("Couldn't reach server. Check your internet connection."))
        }
    }
}