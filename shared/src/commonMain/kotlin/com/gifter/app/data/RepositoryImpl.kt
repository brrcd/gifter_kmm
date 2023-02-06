package com.gifter.app.data

import com.gifter.app.data.local.LocalSource
import com.gifter.app.data.model.response.JWT
import com.gifter.app.data.model.response.User
import com.gifter.app.data.remote.RemoteSource
import com.gifter.app.data.remote.RequestResult
import com.gifter.app.di.PlatformModule

class RepositoryImpl(
	private val localSource: LocalSource,
	private val remoteSource: RemoteSource
) : Repository {
	override suspend fun verifyGoogleIdToken(idToken: String): RequestResult<JWT> {
		val result = remoteSource.verifyGoogleIdToken(idToken)
		if (result is RequestResult.Success) {
			localSource.setJWT(result.data.token)
		}
		return result
	}
	
	override suspend fun registerUser(id: String, name: String): RequestResult<User> {
		return remoteSource.registerNewUser(id, name)
	}
	
	override suspend fun getUser(): RequestResult<User> {
		return remoteSource.getUser()
	}
	
	override fun getJWT(): String = localSource.getJWT()
	
	override fun clearSettings() = localSource.clearSettings()
	
	// TODO REMOVE
	override fun removeJWT() {
		localSource.removeJWT()
	}
}