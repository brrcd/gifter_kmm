package com.gifter.app.data

import com.gifter.app.data.model.response.JWT
import com.gifter.app.data.model.response.User
import com.gifter.app.data.remote.RemoteSource
import com.gifter.app.data.remote.RequestResult
import com.gifter.app.util.JWT_TOKEN

class RepositoryImpl(
//	private val localSource: LocalSource,
	private val remoteSource: RemoteSource
) : Repository {
	override suspend fun verifyGoogleIdToken(idToken: String): RequestResult<JWT> {
		val result = remoteSource.verifyGoogleIdToken(idToken)
		JWT_TOKEN = (result as RequestResult.Success).data.token
		return result
	}
	
	override suspend fun registerUser(name: String): RequestResult<User> {
		return remoteSource.registerNewUser(name)
	}
}