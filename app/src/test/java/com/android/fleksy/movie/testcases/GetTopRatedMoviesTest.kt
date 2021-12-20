package com.android.fleksy.movie.testcases

import com.android.fleksy.movie.BaseMovieApiTest
import com.android.fleksy.movie.data.firstMovie
import com.android.fleksy.movie.data.lastMovieInFirstPage
import com.android.fleksy.movie.data.remote.dto.toMovie
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetTopRatedMoviesTest : BaseMovieApiTest() {

    override val responseJsonPath: String
        get() = "top_rated_first_page.json"

    @ExperimentalCoroutinesApi
    @InternalCoroutinesApi
    @Test
    fun `Movie Api parses top rated first page correctly`() {
        val movies = runBlocking {
            movieApi.getMovies(1).movies.map {
                it.toMovie()
            }
        }

        assert(movies.size == 20)

        assert(firstMovie.value == movies.first())

        assert(lastMovieInFirstPage.value == movies.last())
    }

}