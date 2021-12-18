package com.android.fleksy.movie.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class UserSettingsImpl @Inject constructor(
    @ApplicationContext context: Context
) : UserSettings {

    override val themeStream: MutableStateFlow<Boolean>
    override var isDark: Boolean by AppThemePreferenceDelegate("app_theme", true)

    private val preferences: SharedPreferences =
        context.getSharedPreferences("user_settings", Context.MODE_PRIVATE)

    init {
        themeStream = MutableStateFlow(isDark)
    }

    inner class AppThemePreferenceDelegate(
        private val name: String,
        private val default: Boolean,
    ) : ReadWriteProperty<Any?, Boolean> {

        override fun getValue(thisRef: Any?, property: KProperty<*>): Boolean =
            preferences.getBoolean(name, default)

        override fun setValue(thisRef: Any?, property: KProperty<*>, value: Boolean) {
            themeStream.value = value
            preferences.edit {
                putBoolean(name, value)
            }
        }
    }
}