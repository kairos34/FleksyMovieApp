package com.android.fleksy.movie.presentation.movie_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.android.fleksy.movie.R
import com.android.fleksy.movie.preferences.UserSettings
import com.android.fleksy.movie.util.safeClick

@Composable
fun ThemeSwitchItem(
    userSettings: UserSettings,
    onToggleTheme: (Boolean) -> Unit
) {
    val isLight = userSettings.themeStream.collectAsState()
    Box(
        contentAlignment = Alignment.TopEnd
    ) {
        val speed = remember {
            mutableStateOf(1.0f)
        }
        val clipSpec = remember {
            mutableStateOf(
                if (isLight.value) LottieClipSpec.Progress(
                    0.0f,
                    0.0f
                ) else LottieClipSpec.Progress(1.0f, 1.0f)
            )
        }
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.theme))
        LottieAnimation(
            composition,
            modifier = Modifier
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                ) {
                    safeClick {
                        speed.value = if (isLight.value) 1.0f else -1.0f
                        clipSpec.value = LottieClipSpec.Progress(0.0f, 1.0f)
                        onToggleTheme(isLight.value.not())
                    }
                }
                .height(50.dp)
                .width(100.dp),
            clipSpec = clipSpec.value,
            speed = speed.value,
            contentScale = ContentScale.FillWidth
        )
    }
}