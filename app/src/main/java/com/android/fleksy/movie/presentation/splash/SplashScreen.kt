package com.android.fleksy.movie.presentation.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.android.fleksy.movie.R
import com.android.fleksy.movie.presentation.Screen

@Composable
fun SplashScreen(navController: NavController) {
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
        navController.navigate(Screen.MovieListScreen.route)
    }
    Box (
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Image (
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier.scale(scale.value)
        )
    }
}