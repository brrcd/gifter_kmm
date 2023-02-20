package com.gifter.app.data.remote

import com.gifter.app.data.model.response.JWT
import com.gifter.app.data.model.response.User
import com.gifter.app.data.model.response.Wish
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class RemoteSourceImpl(
	private val client: HttpClient
) : RemoteSource {
	
	override suspend fun verifyGoogleIdToken(idToken: String): RequestResult<JWT> {
		return client.processRequest {
			post(AUTH_EP) {
				setBody(hashMapOf("idToken" to idToken))
			}
		}
	}
	
	override suspend fun registerNewUser(id: String, name: String): RequestResult<JWT> {
		return client.processRequest {
			post(USER_EP) {
				setBody(hashMapOf("id" to id, "name" to name))
			}
		}
	}
	
	override suspend fun getUser(): RequestResult<User> {
		return client.processRequest {
			get(USER_EP)
		}
	}
	
	override suspend fun createWish(
		wish: Wish
	): RequestResult<Unit> {
		return client.processRequest {
			post(WISH_EP) {
				setBody(
					wish
				)
			}
		}
	}
	
	override suspend fun getWish(id: Int): RequestResult<Wish> {
		return client.processRequest {
			get(WISH_EP) {
				parameter("id", id)
			}
		}
	}
	
	override suspend fun getAllWishes(): RequestResult<List<Wish>> =
		client.processRequest { get(WISH_ALL_EP) }
	
	companion object {
		private const val AUTH_EP = "auth"
		private const val USER_EP = "user"
		private const val WISH_EP = "wish"
		private const val WISH_ALL_EP = "wish/all"
	}
}