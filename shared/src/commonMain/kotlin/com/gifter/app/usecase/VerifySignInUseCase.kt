package com.gifter.app.usecase

import com.gifter.app.data.Repository
import com.gifter.app.data.model.response.JWT
import com.gifter.app.data.remote.RequestResult

class VerifySignInUseCase(private val repository: Repository) {
	
	suspend operator fun invoke(idToken: String): RequestResult<JWT> = repository.verifyGoogleIdToken(idToken)
	
}