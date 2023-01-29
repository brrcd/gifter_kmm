package com.gifter.app.data

import com.gifter.app.data.model.response.JWT
import com.gifter.app.data.remote.RemoteSource
import com.gifter.app.data.remote.RequestResult

class RepositoryImpl(
//	private val localSource: LocalSource,
	private val remoteSource: RemoteSource
) : Repository {
	override suspend fun verifyGoogleIdToken(idToken: String): RequestResult<JWT> {
		return remoteSource.verifyGoogleIdToken(idToken)
	}
}