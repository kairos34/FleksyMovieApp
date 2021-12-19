package com.android.fleksy.movie.presentation.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.android.fleksy.movie.R
import com.android.fleksy.movie.presentation.splash.components.AppDeveloperItem
import com.android.fleksy.movie.presentation.splash.components.AppLogoItem

@Composable
fun SplashScreen(navController: NavController, isLight: Boolean) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.snow))
    LottieAnimation(composition)
    AppLogoItem(navController = navController, isLight)
    AppDeveloperItem()
}