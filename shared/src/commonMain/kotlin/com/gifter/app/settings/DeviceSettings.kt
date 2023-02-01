package com.gifter.app.settings

import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set

class DeviceSettings(factory: Settings.Factory) {
	
	private val settings = factory.create()
	
	var jwt: String get() { return settings[JWT] ?: "" }
	set(value) { settings[JWT] = value }
	
	companion object {
		private const val JWT = "auth_token"
	}
}