package com.android.fleksy.movie.domain.usecases

import android.util.Log
import com.android.fleksy.movie.common.Resource
import com.android.fleksy.movie.data.remote.dto.toMovie
import com.android.fleksy.movie.domain.model.Movie
import com.android.fleksy.movie.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(tvId: Int): Flow<Resource<Movie>> = flow {
        try {
            emit(Resource.Loading<Movie>())
            val movie = repository.getMovie(tvId).toMovie()
            emit(Resource.Success<Movie>(movie))
        } catch (e: HttpException) {
            emit(
                Resource.Error<Movie>(
                    e.localizedMessage ?: "An unexpected error occured"
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error<Movie>("Couldn't reach server. Check your internet connection."))
        }
    }
}