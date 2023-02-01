package com.gifter.app.data.local

import com.gifter.app.AppDatabase
import com.gifter.app.settings.DeviceSettings

class LocalSourceImpl(
	private val database: AppDatabase,
	private val settings: DeviceSettings
	) : LocalSource {
	
	override fun getJWT(): String = settings.jwt
	
	override fun setJWT(token: String) {
		settings.jwt = token
	}
}