package com.android.fleksy.movie.presentation.movie_detail.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.fleksy.movie.domain.model.Movie
import com.android.fleksy.movie.presentation.common.isScreenModePortrait
import com.android.fleksy.movie.presentation.movie_detail.MovieDetailViewModel
import com.google.accompanist.pager.*
import kotlin.math.absoluteValue

@ExperimentalPagerApi
@Composable
fun MovieDetailPager(
    viewModel: MovieDetailViewModel = hiltViewModel(),
    movies: List<Movie>,
    cardHeight: Dp,
    posterHeight: Dp,
    posterWidth: Dp,
    inverseColor: Color
) {
    HorizontalPager(
        count = movies.size - 1,
        contentPadding = PaddingValues(horizontal = 32.dp),
    ) { page ->
        viewModel.updateCurrentIndex(currentPage)
        val isScreenModePortrait = isScreenModePortrait()
        MovieDetailCard(
            modifier = Modifier
                .graphicsLayer {
                    // Calculate the absolute offset for the current page from the
                    // scroll position. We use the absolute value which allows us to mirror
                    // any effects for both directions
                    val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                    // We animate the scaleX + scaleY, between 90%-95% and 100%
                    lerp(
                        start = if(isScreenModePortrait) 0.9f else 0.95f,
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
                }.height(cardHeight),
            inverseColor = inverseColor
        ) {
            MovieDetailItem(movie = movies[page], posterHeight, posterWidth)
        }
    }
}