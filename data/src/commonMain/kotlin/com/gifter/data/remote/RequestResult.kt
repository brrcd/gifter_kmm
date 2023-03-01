package com.gifter.data.remote

import com.gifter.data.model.response.ApiError

sealed class RequestResult<out V> {
	class Success<out T>(val data: T) : RequestResult<T>()
	class Error(val error: ApiError) : RequestResult<Nothing>()
}
