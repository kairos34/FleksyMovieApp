package com.android.fleksy.movie.testcases

import com.android.fleksy.movie.BaseMovieApiTest
import com.android.fleksy.movie.data.firstMovie
import com.android.fleksy.movie.data.firstSimilarMovieWithTopRated
import com.android.fleksy.movie.data.lastSimilarMovieWithTopRated
import com.android.fleksy.movie.data.remote.dto.toMovie
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetSimilarMoviesTest: BaseMovieApiTest() {
    override val responseJsonPath: String
        get() = "similar_movies_with_top_rated_first.json"

    @ExperimentalCoroutinesApi
    @InternalCoroutinesApi
    @Test
    fun `Movie Api parses similar movies correctly`() {
        val similarMovies = runBlocking {
            movieApi.getSimilarMovies(firstMovie.value.id).movies.map {
                it.toMovie()
            }
        }

        assert(similarMovies.size == 20)

        assert(similarMovies.first() == firstSimilarMovieWithTopRated.value)

        assert(similarMovies.last() == lastSimilarMovieWithTopRated.value)
    }
}