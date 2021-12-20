package com.android.fleksy.movie.data

import java.io.InputStreamReader

class MockResponseFileReader {
    operator fun invoke(path: String): String {
        val content: String
        InputStreamReader(this.javaClass.classLoader!!.getResourceAsStream(path)).use {
            content = it.readText()
        }
        return content
    }
}