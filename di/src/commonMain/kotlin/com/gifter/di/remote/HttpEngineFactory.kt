package com.gifter.di.remote

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory

expect class HttpEngineFactory constructor() {
	fun create(): HttpClientEngineFactory<HttpClientEngineConfig>
}