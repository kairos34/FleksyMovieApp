package com.android.fleksy.movie.presentation.movie_detail.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import coil.compose.rememberImagePainter
import com.android.fleksy.movie.R
import com.android.fleksy.movie.common.Constants
import com.android.fleksy.movie.domain.model.Movie
import com.android.fleksy.movie.presentation.common.*

@Composable
fun MovieDetailItem(movie: Movie, posterHeight: Dp, posterWidth: Dp) {
    val painter = if(movie.posterPath.isNullOrEmpty()) {
        painterResource(id = R.drawable.placeholder)
    } else {
        rememberImagePainter(
            data = "${Constants.LARGE_IMAGE_URL}${movie.posterPath}",
            builder = {
                placeholder(R.drawable.placeholder)
                fallback(R.drawable.placeholder)
            }
        )
    }
    MediaQuery(comparator = Dimensions.Width lessThan Dimensions.Height) {
        Column {
            Image(
                painter = painter,
                contentScale = ContentScale.FillBounds,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(posterHeight)
            )
            MovieDetailInfo(movie = movie)
        }
    }
    MediaQuery(comparator = Dimensions.Width greaterThan Dimensions.Height) {
        Row {
            Image(
                painter = painter,
                contentScale = ContentScale.FillBounds,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(posterWidth)
            )
            MovieDetailInfo(movie = movie)
        }
    }
}