package com.android.fleksy.movie.presentation.movie_detail.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.android.fleksy.movie.domain.model.Movie
import com.google.accompanist.pager.*
import kotlin.math.absoluteValue

@ExperimentalPagerApi
@Composable
fun MovieDetailPager(movies: List<Movie>, currentIndex: MutableState<Int>) {
    HorizontalPager(
        count = movies.size - 1,
        contentPadding = PaddingValues(horizontal = 32.dp),
    ) { page ->
        currentIndex.value = currentPage
        val configuration = LocalConfiguration.current
        val screenHeight = configuration.screenHeightDp.dp
        val screenWidth = configuration.screenWidthDp.dp
        val cardHeight = Dp((screenHeight.value * 0.8).toFloat())
        val cardWidth = Dp((screenWidth.value * 0.9).toFloat())
        val posterHeight = Dp((cardHeight.value * 0.5).toFloat())
        val posterWidth = Dp((cardWidth.value * 0.5).toFloat())
        Card(
            shape = RoundedCornerShape(16.dp),
            backgroundColor = MaterialTheme.colors.onBackground,
            contentColor = MaterialTheme.colors.background,
            modifier = Modifier
                .graphicsLayer {
                    // Calculate the absolute offset for the current page from the
                    // scroll position. We use the absolute value which allows us to mirror
                    // any effects for both directions
                    val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                    // We animate the scaleX + scaleY, between 90% and 100%
                    lerp(
                        start = 0.9f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
                        scaleY = scale
                    }

                    // We animate the alpha, between 50% and 100%
                    alpha = lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                }
                .height(cardHeight)
        ) {
            MovieDetailItem(movie = movies[page], posterHeight, posterWidth)
        }
    }
}