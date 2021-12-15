package com.android.fleksy.movie.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.fleksy.movie.presentation.movie_detail.MovieDetailScreen
import com.android.fleksy.movie.presentation.movie_list.MovieListScreen
import com.android.fleksy.movie.presentation.splash.SplashScreen
import com.android.fleksy.movie.presentation.theme.FleksyMovieAppTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FleksyMovieAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Navigation()
                }
            }
        }
    }
}

@ExperimentalPagerApi
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {
        composable(
            route = Screen.SplashScreen.route
        ) {
            SplashScreen(navController)
        }
        composable (
            route = Screen.MovieListScreen.route
        ) {
            MovieListScreen(navController)
        }
        composable(
            route = Screen.MovieDetailScreen.route + "/{tvId}"
        ) {
            MovieDetailScreen()
        }
    }
}