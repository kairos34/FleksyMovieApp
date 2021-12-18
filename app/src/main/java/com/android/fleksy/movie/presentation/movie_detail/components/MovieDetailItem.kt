package com.android.fleksy.movie.presentation.movie_detail.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.Dp
import coil.ImageLoader
import com.android.fleksy.movie.R
import com.android.fleksy.movie.common.Constants
import com.android.fleksy.movie.domain.model.Movie
import com.android.fleksy.movie.presentation.common.*
import com.android.fleksy.movie.presentation.theme.LightGray
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun MovieDetailItem(movie: Movie, posterHeight: Dp, posterWidth: Dp) {
    MediaQuery(comparator = Dimensions.Width lessThan Dimensions.Height) {
        Column {
            CoilImage(
                imageModel = "${Constants.LARGE_IMAGE_URL}${movie.posterPath}",
                contentDescription = null,
                imageLoader = {
                    ImageLoader.Builder(LocalContext.current)
                        .availableMemoryPercentage(0.25)
                        .build()
                },
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(posterHeight),
                shimmerParams = ShimmerParams(
                    baseColor = MaterialTheme.colors.background,
                    highlightColor = LightGray,
                    durationMillis = 350,
                    dropOff = 0.65f,
                    tilt = 20f
                ),
                error = ImageBitmap.imageResource(R.drawable.placeholder)
            )
            MovieDetailInfo(movie = movie)
        }
    }
    MediaQuery(comparator = Dimensions.Width greaterThan Dimensions.Height) {
        Row {
            CoilImage(
                imageModel = "${Constants.LARGE_IMAGE_URL}${movie.posterPath}",
                contentDescription = null,
                imageLoader = {
                    ImageLoader.Builder(LocalContext.current)
                        .availableMemoryPercentage(0.25)
                        .build()
                },
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(posterWidth),
                shimmerParams = ShimmerParams(
                    baseColor = MaterialTheme.colors.background,
                    highlightColor = LightGray,
                    durationMillis = 350,
                    dropOff = 0.65f,
                    tilt = 20f
                ),
                error = ImageBitmap.imageResource(R.drawable.placeholder)
            )
            MovieDetailInfo(movie = movie)
        }
    }
}