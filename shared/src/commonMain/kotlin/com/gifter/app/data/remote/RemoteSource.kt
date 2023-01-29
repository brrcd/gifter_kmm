package com.gifter.app.data.remote

import com.gifter.app.data.model.response.JWT

interface RemoteSource {
	suspend fun verifyGoogleIdToken(idToken: String): RequestResult<JWT>
}