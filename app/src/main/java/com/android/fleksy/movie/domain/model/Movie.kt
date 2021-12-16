package com.android.fleksy.movie.domain.model

data class Movie(
    val id: Int = 0,
    val name: String = "",
    val vote: Double = 0.0,
    val posterPath: String? = null,
    val overview: String = "",
    val date: String? = null
)