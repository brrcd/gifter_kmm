package com.gifter.app.settings

import com.gifter.app.Platform
import com.russhwolf.settings.Settings

expect class SettingsFactory constructor(platform: Platform) {
	fun create(): Settings.Factory
}