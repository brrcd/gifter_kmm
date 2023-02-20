package com.gifter.app.data.remote

import com.gifter.app.data.model.response.JWT
import com.gifter.app.data.model.response.User
import com.gifter.app.data.model.response.Wish

interface RemoteSource {
	suspend fun verifyGoogleIdToken(idToken: String): RequestResult<JWT>
	suspend fun registerNewUser(id: String, name: String): RequestResult<JWT>
	suspend fun getUser(): RequestResult<User>
	
	// WISH
	suspend fun createWish(wish: Wish): RequestResult<Unit>
	suspend fun getWish(id: Int): RequestResult<Wish>
	suspend fun getAllWishes(): RequestResult<List<Wish>>
}