package com.android.fleksy.movie.presentation.splash.components

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.android.fleksy.movie.R
import com.android.fleksy.movie.presentation.Screen
import com.android.fleksy.movie.presentation.common.IsScreenModePortrait

@Composable
fun AppLogoItem(navController: NavController) {
    val scale = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 1.6f,
            animationSpec = tween(
                durationMillis = 3000,
                delayMillis = 100,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                }
            )
        )
        navController.navigate(Screen.MovieListScreen.route) {
            popUpTo(Screen.SplashScreen.route) {
                inclusive = true
            }
        }
    }
    val isScreenModePortrait = IsScreenModePortrait()
    Box(
        contentAlignment = if (isScreenModePortrait) Alignment.Center else Alignment.TopCenter,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .scale(scale.value)
                .padding(
                    if (isScreenModePortrait) 0.dp else Dp((LocalConfiguration.current.screenHeightDp.dp.value * 0.25).toFloat())
                )
        )
    }
}