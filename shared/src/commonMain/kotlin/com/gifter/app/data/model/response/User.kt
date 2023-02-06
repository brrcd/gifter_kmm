package com.gifter.app.data.model.response

@kotlinx.serialization.Serializable
data class User(
	val id: String,
	val name: String
) {
	val isRegistrationComplete = name.isNotEmpty()
}