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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.android.fleksy.movie.presentation.common.ErrorItem
import com.android.fleksy.movie.presentation.common.LoadingView
import com.android.fleksy.movie.presentation.common.MarqueeText
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
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item { Spacer(modifier = Modifier.height(20.dp)) }
                item {
                    Text(
                        text = "Similar Movies With:",
                        style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.ExtraBold),
                        textAlign = TextAlign.Center
                    )
                }
                item {
                    MarqueeText(
                        text = it.first().name,
                        style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.ExtraBold),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                item { MovieDetailPager(it, currentIndex) }
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