package com.gifter.app.data

import com.gifter.app.data.model.response.JWT
import com.gifter.app.data.remote.RequestResult

interface Repository {
	suspend fun verifyGoogleIdToken(idToken: String): RequestResult<JWT>
}