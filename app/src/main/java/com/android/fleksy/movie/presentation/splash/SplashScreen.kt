package com.android.fleksy.movie.presentation.splash

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.android.fleksy.movie.presentation.splash.components.AppDeveloperItem
import com.android.fleksy.movie.presentation.splash.components.AppLogoItem

@Composable
fun SplashScreen(navController: NavController, dark: Boolean) {
    AppLogoItem(navController = navController, dark)
    AppDeveloperItem()
}