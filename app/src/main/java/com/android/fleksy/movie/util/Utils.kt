package com.android.fleksy.movie.util

import android.graphics.Bitmap
import android.os.SystemClock
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.graphics.alpha
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.graphics.red
import androidx.palette.graphics.Palette

private var lastClickTime: Long = 0

fun safeClick(function: () -> Unit) {
    if (SystemClock.elapsedRealtime() - lastClickTime < 1000) {
        return
    }
    lastClickTime = SystemClock.elapsedRealtime()
    function()
}

fun Bitmap.generateDominantColorState(): Palette.Swatch = Palette.Builder(this)
    .resizeBitmapArea(0)
    .maximumColorCount(16)
    .generate()
    .swatches
    .maxByOrNull { swatch -> swatch.population }!!

fun getComplimentColor(color: Color): Color {
    val graphicColor = android.graphics.Color.argb(
        color.toArgb().alpha,
        color.toArgb().red,
        color.toArgb().green,
        color.toArgb().blue
    )
    // get existing colors
    val alpha: Int = android.graphics.Color.alpha(graphicColor)
    var red: Int = android.graphics.Color.red(graphicColor)
    var blue: Int = android.graphics.Color.blue(graphicColor)
    var green: Int = android.graphics.Color.green(graphicColor)
    // find compliments
    red = red.inv() and 0xff
    blue = blue.inv() and 0xff
    green = green.inv() and 0xff
    return Color(android.graphics.Color.argb(alpha, red, green, blue))
}