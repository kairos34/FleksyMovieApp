package com.android.fleksy.movie.presentation.movie_detail

import com.android.fleksy.movie.domain.model.Movie

data class MovieDetailState(
    val isLoading: Boolean = false,
    val similarMovies: List<Movie>? = null,
    val error: String = ""
)