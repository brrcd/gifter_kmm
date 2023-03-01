package com.gifter.data

import com.gifter.data.model.response.JWT
import com.gifter.data.model.response.User
import com.gifter.data.model.response.Wish
import com.gifter.data.remote.RequestResult

interface Repository {
	suspend fun verifyGoogleIdToken(idToken: String): RequestResult<JWT>
	suspend fun registerUser(id: String, name: String): RequestResult<JWT>
	suspend fun getUser(): RequestResult<User>
	fun getAuthToken(): String
	fun getRegistrationToken(): String
	fun clearSettings()
	// TODO REMOVE
	fun removeJWT()
	suspend fun createWish(name: String, description: String, urls: List<String>): RequestResult<Unit>
	suspend fun getWish(id: Int): RequestResult<Wish>
	suspend fun getAllWishes(): RequestResult<List<Wish>>
}