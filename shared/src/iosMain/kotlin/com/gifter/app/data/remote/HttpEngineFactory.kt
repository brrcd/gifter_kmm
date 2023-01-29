package com.gifter.app.data.remote

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.ios.Ios

actual class HttpEngineFactory {
	actual fun create(): HttpClientEngineFactory<HttpClientEngineConfig> = Ios
}