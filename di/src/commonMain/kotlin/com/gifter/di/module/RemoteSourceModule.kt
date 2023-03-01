package com.gifter.di.module

import com.gifter.data.local.DeviceSettings
import com.gifter.data.remote.RemoteSource
import com.gifter.data.remote.RemoteSourceImpl
import com.gifter.di.remote.HttpEngineFactory
import com.gifter.di.util.BACK_END_URL
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.accept
import io.ktor.client.request.bearerAuth
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal class RemoteSourceModule(settings: DeviceSettings) {
	private val httpClient =
		HttpClient(HttpEngineFactory().create()) {
			install(Logging) {
				logger = Logger.SIMPLE
				level = LogLevel.ALL
			}
			install(ContentNegotiation) {
				json(
					json = Json {
						expectSuccess = true
						isLenient = true
						ignoreUnknownKeys = true
						prettyPrint = true
						useDefaultTransformers = true
					}
				)
			}
			install(HttpTimeout) {
				connectTimeoutMillis = 10000
				requestTimeoutMillis = 10000
			}
			install(DefaultRequest)
			
			defaultRequest {
				url(BACK_END_URL)
				contentType(Json)
				accept(Json)
				bearerAuth(
					settings.authToken.ifEmpty {
						settings.registrationToken
					}
				)
			}
		}
	
	val remoteSource: RemoteSource = RemoteSourceImpl(httpClient)
}