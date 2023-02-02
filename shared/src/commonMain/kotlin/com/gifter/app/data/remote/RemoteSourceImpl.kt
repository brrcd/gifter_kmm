package com.gifter.app.data.remote

import com.gifter.app.data.model.response.JWT
import com.gifter.app.data.model.response.User
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class RemoteSourceImpl(
	private val client: HttpClient
) : RemoteSource {
	
	override suspend fun verifyGoogleIdToken(idToken: String): RequestResult<JWT> {
		return client.processRequest {
			post("auth") {
				setBody(hashMapOf("idToken" to idToken))
			}
		}
	}
	
	override suspend fun registerNewUser(name: String): RequestResult<User> {
		return client.processRequest {
			post("register") {
				setBody(hashMapOf("name" to name))
			}
		}
	}
	
	override suspend fun getUser(): RequestResult<User> {
		return client.processRequest {
			get("user")
		}
	}
}