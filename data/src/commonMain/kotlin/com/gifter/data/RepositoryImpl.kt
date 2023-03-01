package com.gifter.data

import com.gifter.data.local.LocalSource
import com.gifter.data.model.response.JWT
import com.gifter.data.model.response.User
import com.gifter.data.model.response.Wish
import com.gifter.data.remote.RemoteSource
import com.gifter.data.remote.RequestResult

class RepositoryImpl(
	private val localSource: LocalSource,
	private val remoteSource: RemoteSource
) : Repository {
	
	override suspend fun verifyGoogleIdToken(idToken: String): RequestResult<JWT> {
		val result = remoteSource.verifyGoogleIdToken(idToken)
		if (result is RequestResult.Success) {
			if (result.data.authToken.isNotEmpty()) {
				localSource.setAuthToken(result.data.authToken)
			} else if (result.data.registrationToken.isNotEmpty()) {
				localSource.setRegistrationToken(result.data.registrationToken)
			}
		}
		return result
	}
	
	override suspend fun registerUser(id: String, name: String): RequestResult<JWT> {
		val result = remoteSource.registerNewUser(id, name)
		if (result is RequestResult.Success) {
			if (result.data.authToken.isNotEmpty()) {
				localSource.setAuthToken(result.data.authToken)
			}
		}
		return result
	}
	
	override suspend fun getUser(): RequestResult<User> {
		return remoteSource.getUser()
	}
	
	override fun getAuthToken(): String = localSource.getAuthToken()
	
	override fun getRegistrationToken(): String = localSource.getRegistrationToken()
	
	override fun clearSettings() = localSource.clearSettings()
	
	// TODO REMOVE
	override fun removeJWT() {
		localSource.removeAuthToken()
	}
	
	override suspend fun createWish(
		name: String,
		description: String,
		urls: List<String>
	): RequestResult<Unit> {
		return remoteSource.createWish(Wish(name = name, description = description, urls = urls))
	}
	
	override suspend fun getWish(id: Int): RequestResult<Wish> = remoteSource.getWish(id)
	
	override suspend fun getAllWishes(): RequestResult<List<Wish>> = remoteSource.getAllWishes()
}