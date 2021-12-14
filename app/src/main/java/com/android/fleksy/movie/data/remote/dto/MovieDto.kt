package com.android.fleksy.movie.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MovieDto(
    val page: Int,
    val results: List<ResultDto>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)