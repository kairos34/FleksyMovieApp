package com.android.fleksy.movie.presentation.movie_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.android.fleksy.movie.preferences.UserSettings
import com.android.fleksy.movie.presentation.Screen
import com.android.fleksy.movie.presentation.common.ErrorItem
import com.android.fleksy.movie.presentation.common.LoadingItem
import com.android.fleksy.movie.presentation.common.LoadingView
import com.android.fleksy.movie.presentation.movie_list.components.MovieListItem
import com.android.fleksy.movie.presentation.movie_list.components.ThemeSwitchItem

@Composable
fun MovieListScreen(
    navController: NavController,
    userSettings: UserSettings,
    viewModel: MovieListViewModel = hiltViewModel(),
    onToggleTheme: (Boolean) -> Unit
) {
    val movies = viewModel.getMovies().collectAsLazyPagingItems()
    Box(modifier = Modifier.fillMaxSize()) {
        Column (
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Top Rated Movies",
                    style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.ExtraBold),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(
                            start = 20.dp,
                            end = 20.dp
                        )
                )
                ThemeSwitchItem(userSettings, onToggleTheme)
            }

            LazyColumn(modifier = Modifier.fillMaxSize()) {

                items(movies.itemCount) { index ->
                    MovieListItem(
                        movie = movies[index]!!,
                        onItemClick = {
                            navController.navigate(Screen.MovieDetailScreen.route + "/${it.id}")
                        }
                    )
                }

                movies.apply {
                    when {
                        loadState.refresh is LoadState.Loading -> {
                            item { LoadingView(modifier = Modifier.fillParentMaxSize()) }
                        }
                        loadState.append is LoadState.Loading -> {
                            item { LoadingItem() }
                        }
                        loadState.refresh is LoadState.Error -> {
                            val error = movies.loadState.refresh as LoadState.Error
                            item {
                                ErrorItem(
                                    message = error.error.localizedMessage!!,
                                    modifier = Modifier.fillParentMaxSize(),
                                    onClickRetry = { retry() }
                                )
                            }
                        }
                        loadState.append is LoadState.Error -> {
                            val error = movies.loadState.append as LoadState.Error
                            item {
                                ErrorItem(
                                    message = error.error.localizedMessage!!,
                                    modifier = Modifier.fillParentMaxSize(),
                                    onClickRetry = { retry() }
                                )
                            }
                        }
                    }
                }

            }
        }
    }
}