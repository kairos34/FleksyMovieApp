package com.android.fleksy.movie.util

import android.graphics.Bitmap
import android.os.SystemClock
import androidx.palette.graphics.Palette
import com.android.fleksy.movie.common.Constants
import okhttp3.Interceptor

private var lastClickTime: Long = 0

fun Interceptor.Chain.apiKeyInterceptor() =
    proceed(
        request().newBuilder().url(
            request().url.newBuilder().addQueryParameter(Constants.PARAM_API_KEY, Constants.API_KEY)
                .build()
        ).build()
    )

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