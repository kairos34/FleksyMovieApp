package com.android.fleksy.movie.presentation.movie_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.fleksy.movie.presentation.movie_detail.components.MovieDetailPager
import com.android.fleksy.movie.presentation.theme.ColorPrimary
import com.android.fleksy.movie.presentation.theme.DarkGray
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@ExperimentalPagerApi
@Composable
fun MovieDetailScreen(
    viewModel: MovieDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val isRefreshing by viewModel.isRefreshing.collectAsState()
    Box(modifier = Modifier
        .fillMaxSize()
        .background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    ColorPrimary,
                    DarkGray
                )
            )
        )) {
        state.similarMovies?.let {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item { Spacer(modifier = Modifier.height(20.dp)) }
                item {
                    Text(
                        text = "Similar Movies to:\n" + it.first().name,
                        style = typography.h5.copy(fontWeight = FontWeight.ExtraBold),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                item { MovieDetailPager(it) }
            }
        }
        // If there is an error allow user to refresh UI by swiping
        if (state.error.isNotBlank()) {
            SwipeRefresh(
                state = rememberSwipeRefreshState(isRefreshing),
                onRefresh = { viewModel.refresh() },
            ) {
               LazyColumn(
                   modifier = Modifier.fillMaxSize(),
                   horizontalAlignment = Alignment.CenterHorizontally,
               ) {
                   item {
                       Text(
                           text = state.error,
                           color = MaterialTheme.colors.error,
                           textAlign = TextAlign.Center,
                           modifier = Modifier.fillMaxWidth()
                               .padding(horizontal = 20.dp)
                               .align(Alignment.Center),
                           style = typography.h6,
                       )
                   }
               }
            }
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}