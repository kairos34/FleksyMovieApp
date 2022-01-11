package com.android.fleksy.movie.data.remote.dto

import androidx.annotation.Keep
import com.android.fleksy.movie.domain.model.Movie
import com.google.gson.annotations.SerializedName

@Keep
data class MovieDto(
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    val id: Int,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalName: String,
    val overview: String,
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("release_date")
    val releaseDate: String,
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int
)

fun MovieDto.toMovie() = Movie(
    id = id,
    name = originalName,
    vote = voteAverage,
    posterPath = posterPath,
    overview = overview,
    date = releaseDate
)