package com.gifter.app.data.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VerifyToken(
	@SerialName("idToken")
	val idToken: String
)
