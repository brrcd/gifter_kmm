package com.gifter.di.settings

import com.gifter.di.Platform
import com.russhwolf.settings.Settings

expect class SettingsFactory constructor(platform: Platform) {
	fun create(): Settings.Factory
}