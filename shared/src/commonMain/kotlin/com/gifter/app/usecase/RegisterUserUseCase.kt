package com.gifter.app.usecase

import com.gifter.app.data.Repository
import com.gifter.app.data.model.response.User
import com.gifter.app.data.remote.RequestResult

class RegisterUserUseCase(private val repository: Repository) {
	
	suspend operator fun invoke(name: String) : RequestResult<User> = repository.registerUser(name)
	
}