package com.gifter.data.local

import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set

class DeviceSettings(factory: Settings.Factory) {
	
	private val settings = factory.create()
	
	var authToken: String
		get() {
			return settings[AUTH_TOKEN] ?: ""
		}
		set(value) {
			settings[AUTH_TOKEN] = value
		}
	
	var registrationToken: String
		get() {
			return settings[REGISTRATION_TOKEN] ?: ""
		}
		set(value) {
			settings[REGISTRATION_TOKEN] = value
		}
	
	fun clear() = settings.clear()
	
	fun clearRegistrationToken() { registrationToken = "" }
	
	fun clearAuthToken() { authToken = "" }
	
	companion object {
		private const val AUTH_TOKEN = "auth_token"
		private const val REGISTRATION_TOKEN = "registration_token"
	}
}