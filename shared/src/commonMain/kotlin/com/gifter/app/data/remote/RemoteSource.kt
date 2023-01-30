package com.gifter.app.data.remote

import com.gifter.app.data.model.response.JWT
import com.gifter.app.data.model.response.User

interface RemoteSource {
	suspend fun verifyGoogleIdToken(idToken: String): RequestResult<JWT>
	suspend fun registerNewUser(name: String): RequestResult<User>
}