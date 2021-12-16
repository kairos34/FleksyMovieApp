package com.android.fleksy.movie.presentation.movie_detail

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.android.fleksy.movie.common.Constants
import com.android.fleksy.movie.presentation.movie_detail.components.MovieDetailList
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@Composable
fun MovieDetailScreen(
    viewModel: MovieDetailViewModel = hiltViewModel()
) {
    val currentMovie = viewModel.movie.value
    val currentIndex = viewModel.index
    val state = viewModel.state.value
    val brush = viewModel.brush

    viewModel.updateCurrentMovie()

    val painter = rememberImagePainter(
        data = "${Constants.SMALL_IMAGE_URL}${currentMovie.posterPath}",
        builder = {
            allowHardware(false)
        }
    )

    viewModel.updateBrushByCurrentMovieColor(painter)

    MovieDetailList(state, currentIndex, brush, viewModel::refresh)
}
