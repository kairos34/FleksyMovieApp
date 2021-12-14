package com.android.fleksy.movie.data.remote.dto

import com.android.fleksy.movie.domain.model.Movie
import com.android.fleksy.movie.domain.model.MovieDetail
import com.google.gson.annotations.SerializedName

data class MovieDto(
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("first_air_date")
    val firstAirDate: String,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    val id: Int,
    val name: String,
    @SerializedName("origin_country")
    val originCountry: List<String>,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_name")
    val originalName: String,
    val overview: String,
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String?,
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
    date = firstAirDate
)

fun MovieDto.toMovieDetail() = MovieDetail(
    id = id,
    name = originalName,
    vote = voteAverage,
    posterPath = posterPath,
    overview = overview,
    date = firstAirDate
)