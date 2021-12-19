package com.android.fleksy.movie.preferences

import kotlinx.coroutines.flow.StateFlow

interface UserSettings {
    val themeStream: StateFlow<Boolean>
    var isLight: Boolean
}