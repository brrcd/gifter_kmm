package com.gifter.app.di

import com.gifter.app.util.BACK_END_URL
import com.gifter.app.util.REMOTE_DATA_SOURCE_DI_MODULE
import com.gifter.app.data.remote.HttpEngineFactory
import com.gifter.app.data.remote.RemoteSource
import com.gifter.app.data.remote.RemoteSourceImpl
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
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

class RemoteSourceModule: DIModule {
	override val module = DI.Module(name = REMOTE_DATA_SOURCE_DI_MODULE) {
		bind<HttpClient>() with singleton {
			HttpClient(HttpEngineFactory().create()) {
				install(Logging) {
					logger = Logger.SIMPLE
					level = LogLevel.ALL
				}
				install(ContentNegotiation) {
					json(
						json = Json {
							isLenient = true
							ignoreUnknownKeys = true
							prettyPrint = true
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
					contentType(ContentType.Application.Json)
					accept(ContentType.Application.Json)
				}
			}
		}
		bind<RemoteSource>() with singleton {
			RemoteSourceImpl(instance())
		}
	}
}