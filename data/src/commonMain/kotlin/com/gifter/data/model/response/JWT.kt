package com.gifter.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class JWT(
	val authToken: String = "",
	val registrationToken: String = ""
)