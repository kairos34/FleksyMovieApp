package com.android.fleksy.movie.presentation.movie_detail

import android.graphics.drawable.BitmapDrawable
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.android.fleksy.movie.common.Constants
import com.android.fleksy.movie.presentation.movie_detail.components.MovieDetailList
import com.android.fleksy.movie.presentation.theme.ColorPrimary
import com.android.fleksy.movie.presentation.theme.DarkGray
import com.android.fleksy.movie.util.generateDominantColorState
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@Composable
fun MovieDetailScreen(
    viewModel: MovieDetailViewModel = hiltViewModel()
) {
    val currentMovie = viewModel.movie.value
    val currentIndex = viewModel.index
    val state = viewModel.state.value
    val brush = remember {
        mutableStateOf(
            Brush.verticalGradient(
                colors = listOf(ColorPrimary, DarkGray)
            )
        )
    }

    viewModel.updateCurrentMovie()

    val painter = rememberImagePainter(
        data = "${Constants.SMALL_IMAGE_URL}${currentMovie.posterPath}",
        builder = {
            allowHardware(false)
        }
    )

    currentMovie.posterPath?.takeIf { it.isNotEmpty() }?.run {
        rememberCoroutineScope().launch {
            val image =
                (painter.imageLoader.execute(painter.request).drawable as BitmapDrawable?)?.bitmap
            val swatch = image?.generateDominantColorState()
            swatch?.let {
                brush.value = Brush.verticalGradient(
                    listOf(Color(swatch.rgb), DarkGray)
                )
            }
        }
    } ?: run {
        brush.value = Brush.verticalGradient(
            listOf(ColorPrimary, DarkGray)
        )
    }

    MovieDetailList(state, currentIndex, brush, viewModel::refresh)
}
