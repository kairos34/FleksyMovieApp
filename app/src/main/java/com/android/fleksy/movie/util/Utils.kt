package com.android.fleksy.movie.util

import android.os.SystemClock
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