package com.android.fleksy.movie.presentation.movie_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.android.fleksy.movie.presentation.common.*
import com.android.fleksy.movie.presentation.movie_detail.MovieDetailState
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@Composable
fun MovieDetailList(
    state: MovieDetailState,
    currentIndex: MutableState<Int>,
    brush: MutableState<Brush>,
    retry: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = brush.value)
    ) {
        state.similarMovies?.let {
            val configuration = LocalConfiguration.current
            val screenHeight = configuration.screenHeightDp.dp
            val screenWidth = configuration.screenWidthDp.dp
            val cardHeight = Dp((screenHeight.value * 0.8).toFloat())
            val cardWidth = Dp((screenWidth.value * 0.9).toFloat())
            val posterHeight = Dp((cardHeight.value * 0.5).toFloat())
            val posterWidth = Dp((cardWidth.value * 0.5).toFloat())
            Column (
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (it.size == 1) {
                    MediaQuery(comparator = Dimensions.Width lessThan Dimensions.Height) {
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = "Similar Movies Not Found With:",
                            style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.ExtraBold),
                            textAlign = TextAlign.Center
                        )
                        MarqueeText(
                            text = it.first().name,
                            style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.ExtraBold),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                    MediaQuery(comparator = Dimensions.Width greaterThan Dimensions.Height) {
                        MarqueeText(
                            text = "Similar Movies Not Found With: ${it.first().name}",
                            style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.ExtraBold),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                    MovieDetailCard(
                        modifier = Modifier.padding(
                            start = 32.dp,
                            end = 32.dp
                        ).height(cardHeight)
                    ) {
                        MovieDetailItem(
                            movie = it.first(),
                            posterHeight = posterHeight,
                            posterWidth = posterWidth
                        )
                    }
                } else {
                    MediaQuery(comparator = Dimensions.Width lessThan Dimensions.Height) {
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = "Similar Movies With:",
                            style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.ExtraBold),
                            textAlign = TextAlign.Center
                        )
                        MarqueeText(
                            text = it.first().name,
                            style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.ExtraBold),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                    MediaQuery(comparator = Dimensions.Width greaterThan Dimensions.Height) {
                        MarqueeText(
                            text = "Similar Movies With: ${it.first().name}",
                            style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.ExtraBold),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        item { MovieDetailPager(it, currentIndex, cardHeight, posterHeight, posterWidth) }
                    }
                }
            }
        }
        // If there is an error allow user to refresh UI
        if (state.error.isNotBlank()) {
            ErrorItem(
                message = state.error,
                modifier = Modifier.fillMaxSize(),
                onClickRetry = { retry() }
            )
        }
        if (state.isLoading) {
            LoadingView(modifier = Modifier.align(Alignment.Center))
        }
    }
}