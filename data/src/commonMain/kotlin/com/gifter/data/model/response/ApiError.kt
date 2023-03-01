package com.gifter.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiError(
	@SerialName("errorCode")
	val errorCode: Int,
	@SerialName("errorMessage")
	val errorMessage: String
)