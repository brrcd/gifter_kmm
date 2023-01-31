package com.gifter.app.settings

import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set

class DeviceSettings(factory: Settings.Factory) {
	
	private val settings = factory.create()
	
	var authToken: String get() = run { settings[AUTH_TOKEN] ?: "" }
	set(value) = run { settings[AUTH_TOKEN] = value }
	
	companion object {
		private const val AUTH_TOKEN = "auth_token"
	}
}