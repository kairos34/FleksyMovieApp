package com.android.fleksy.movie.util

import com.android.fleksy.movie.common.Constants
import okhttp3.Interceptor

fun Interceptor.Chain.apiKeyInterceptor() =
    proceed(
        request().newBuilder().url(
            request().url.newBuilder().addQueryParameter(Constants.PARAM_API_KEY, Constants.API_KEY)
                .build()
        ).build()
    )
