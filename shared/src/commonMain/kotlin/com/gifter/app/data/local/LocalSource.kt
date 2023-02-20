package com.gifter.app.data.local

interface LocalSource {
	fun getAuthToken(): String
	fun setAuthToken(token: String)
	fun getRegistrationToken(): String
	fun setRegistrationToken(token: String)
	fun clearSettings()
	
	// TODO REMOVE
	fun removeAuthToken()
}