package com.android.fleksy.movie.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MovieListDto(
    val page: Int,
    @SerializedName("results")
    val movies: List<MovieDto>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)