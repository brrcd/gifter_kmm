package com.gifter.app.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class User(
	val id: String,
	val name: String
)