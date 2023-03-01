package com.gifter.di.settings

import com.gifter.di.Platform
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings

actual class SettingsFactory actual constructor(private val platform: Platform) {
	actual fun create(): Settings.Factory = SharedPreferencesSettings.Factory(platform.context)
}