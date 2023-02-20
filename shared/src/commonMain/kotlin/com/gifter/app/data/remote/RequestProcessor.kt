package com.gifter.app.data.remote

import com.gifter.app.data.model.response.ApiError
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess

suspend inline fun <reified T> HttpClient.processRequest(call: HttpClient.() -> HttpResponse): RequestResult<T> {
	return try {
		val result = call.invoke(this)
		if (result.status.isSuccess()) {
			return RequestResult.Success(result.body())
		} else {
			// todo remove
			println("Server error at request processing :\n" + result.status)
			processServerError(result)
		}
	} catch (e: Exception) {
		// todo remove
		val errorMessage = e.message ?: "unknown error"
		println("Exception at request processing :\n$errorMessage")
		RequestResult.Error(ApiError(0, errorMessage))
	}
}

suspend inline fun processServerError(result: HttpResponse): RequestResult.Error {
	val error: ApiError = result.body()
	return RequestResult.Error(error)
}