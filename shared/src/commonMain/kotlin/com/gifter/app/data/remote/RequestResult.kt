package com.gifter.app.data.remote

sealed class RequestResult<out V> {
	class Success<out T>(val data: T) : RequestResult<T>()
	class Error(val error: com.gifter.app.data.model.response.ApiError) : RequestResult<Nothing>()
}
