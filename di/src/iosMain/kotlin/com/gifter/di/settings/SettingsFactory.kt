package com.gifter.di.settings

import com.gifter.di.Platform
import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings

actual class SettingsFactory actual constructor(private val platform: Platform){
	actual fun create(): Settings.Factory = NSUserDefaultsSettings.Factory()
}