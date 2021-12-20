package com.android.fleksy.movie.testcases

import com.android.fleksy.movie.BaseMovieApiTest
import com.android.fleksy.movie.data.firstMovie
import com.android.fleksy.movie.data.remote.dto.toMovie
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetMovieTest: BaseMovieApiTest() {

    override val responseJsonPath: String
        get() = "top_rated_first_movie_detail.json"

    @ExperimentalCoroutinesApi
    @InternalCoroutinesApi
    @Test
    fun `Movie Api parses movie by id correctly`() {
        val movie = runBlocking {
            movieApi.getMovie(firstMovie.value.id).toMovie()
        }

        assert(firstMovie.value == movie)
    }
}