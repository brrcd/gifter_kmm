package com.gifter.app.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class Wish(
	val id: Int = 0,
	val name: String,
	val description: String,
	val urls: List<String>
)