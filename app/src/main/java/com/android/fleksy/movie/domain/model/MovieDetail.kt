package com.android.fleksy.movie.domain.model

data class MovieDetail(
    val id: Int,
    val name: String,
    val vote: Double,
    val posterPath: String,
    val overview: String
)