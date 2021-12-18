package com.android.fleksy.movie.presentation.movie_detail

import android.graphics.drawable.BitmapDrawable
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import coil.request.ImageRequest
import com.android.fleksy.movie.common.Constants
import com.android.fleksy.movie.presentation.movie_detail.components.MovieDetailList
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
    val primary = MaterialTheme.colors.background
    val background = MaterialTheme.colors.background
    val detailTextColor = remember {
        mutableStateOf(
            DarkGray
        )
    }
    val brush = remember {
        mutableStateOf(
            Brush.verticalGradient(
                colors = listOf(primary, background)
            )
        )
    }

    viewModel.updateCurrentMovie()

    val imageRequest = ImageRequest.Builder(LocalContext.current)
        .data(data = "${Constants.SMALL_IMAGE_URL}${currentMovie.posterPath}")
        .allowHardware(false)
        .build()
    val imageLoader = ImageLoader(LocalContext.current)

    currentMovie.posterPath?.takeIf { it.isNotEmpty() }?.run {
        rememberCoroutineScope().launch {
            val image = (imageLoader.execute(imageRequest).drawable as BitmapDrawable?)?.bitmap
            val swatch = image?.generateDominantColorState()
            swatch?.let {
                with(Color(it.rgb)) {
                    brush.value = Brush.verticalGradient(
                        listOf(this, background)
                    )
                    detailTextColor.value = this
                }
            }
        }
    } ?: run {
        brush.value = Brush.verticalGradient(
            listOf(MaterialTheme.colors.primary, background)
        )
    }

    MovieDetailList(state, currentIndex, brush, detailTextColor, viewModel::refresh)
}
