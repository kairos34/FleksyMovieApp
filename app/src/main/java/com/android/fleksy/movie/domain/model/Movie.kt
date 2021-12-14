package com.android.fleksy.movie.domain.model

data class Movie(
    val id: Int,
    val name: String,
    val vote: Double,
    val posterPath: String
)