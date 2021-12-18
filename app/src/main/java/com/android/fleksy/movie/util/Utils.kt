package com.android.fleksy.movie.util

import android.graphics.Bitmap
import android.os.SystemClock
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