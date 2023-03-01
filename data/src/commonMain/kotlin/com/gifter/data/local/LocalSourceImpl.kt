package com.gifter.data.local

import com.gifter.data.AppDatabase

class LocalSourceImpl(
	private val database: AppDatabase,
	private val settings: DeviceSettings
	) : LocalSource {
	
	override fun getAuthToken(): String = settings.authToken
	
	override fun setAuthToken(token: String) {
		settings.authToken = token
		settings.clearRegistrationToken()
	}
	
	override fun getRegistrationToken(): String = settings.registrationToken
	
	override fun setRegistrationToken(token: String) {
		settings.registrationToken = token
		settings.clearAuthToken()
	}
	
	override fun clearSettings() {
		settings.clear()
	}
	
	//TODO REMOVE
	override fun removeAuthToken() {
		settings.authToken = ""
	}
}