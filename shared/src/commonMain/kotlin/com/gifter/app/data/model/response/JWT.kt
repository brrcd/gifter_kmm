package com.gifter.app.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class JWT(
	val token: String
)