package com.android.fleksy.movie.presentation

import android.annotation.SuppressLint

sealed class Screen(val route: String) {
    @SuppressLint("CustomSplashScreen")
    object SplashScreen: Screen("splash_screen")
    object MovieListScreen: Screen("movie_list_screen")
    object MovieDetailScreen: Screen("movie_detail_screen")
}