package com.gifter.app.data.local

interface LocalSource {
	fun getJWT(): String
	fun setJWT(token: String)
	
	// TODO REMOVE
	fun removeJWT()
}