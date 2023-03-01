package com.gifter.di.remote

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.okhttp.OkHttp

actual class HttpEngineFactory {
	actual fun create(): HttpClientEngineFactory<HttpClientEngineConfig> = OkHttp
}