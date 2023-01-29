package com.gifter.app.data.remote

import com.gifter.app.data.model.request.VerifyToken
import com.gifter.app.data.model.response.JWT
import io.ktor.client.HttpClient
import io.ktor.client.call.receive
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class RemoteSourceImpl(
	private val client: HttpClient
) : RemoteSource {
	
	override suspend fun verifyGoogleIdToken(idToken: String): RequestResult<JWT> {
		return client.processRequest<JWT> {
			post("token") {
				setBody(VerifyToken(idToken))
			}
		}
	}
}