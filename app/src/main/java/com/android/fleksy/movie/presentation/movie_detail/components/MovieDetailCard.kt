package com.android.fleksy.movie.presentation.movie_detail.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MovieDetailCard(modifier: Modifier, inverseColor: Color, content: @Composable () -> Unit){
    Card(
        border = BorderStroke(2.dp, inverseColor),
        shape = RoundedCornerShape(10.dp),
        backgroundColor = MaterialTheme.colors.onBackground,
        contentColor = MaterialTheme.colors.background,
        modifier = modifier,
        content = content
    )
}