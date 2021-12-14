package com.android.fleksy.movie.presentation.movie_detail

import com.android.fleksy.movie.domain.model.Movie
import com.android.fleksy.movie.domain.model.MovieDetail

data class MovieDetailState(
    val isLoading: Boolean = false,
    val currentMovie: Movie? = null,
    val similarMovies: List<MovieDetail> = emptyList(),
    val error: String = ""
)