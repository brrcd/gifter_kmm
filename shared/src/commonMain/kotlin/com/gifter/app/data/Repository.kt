package com.gifter.app.data

import com.gifter.app.data.model.response.JWT
import com.gifter.app.data.model.response.User
import com.gifter.app.data.remote.RequestResult

interface Repository {
	suspend fun verifyGoogleIdToken(idToken: String): RequestResult<JWT>
	suspend fun registerUser(name: String): RequestResult<User>
	suspend fun getUser(): RequestResult<User>
	fun getJWT(): String
	// TODO REMOVE
	fun removeJWT()
}