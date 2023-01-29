object Deps {
	private const val composeVersion = "1.3.1"
	private const val kodeinVersion = "7.18.0"
	private const val activityComposeVersion = "1.6.1"
	private const val coroutinesVersion = "1.6.4"
	private const val ktorVersion = "2.2.2"
	private const val kotlinSerializationVersion = "1.4.1"
	private const val sqlDelightVersion = "2.0.0-alpha05"
	private const val googleServicesVersion = "20.4.1"
	private const val lifecycleVersion = "2.5.1"
	
	object Android{
		const val googleAuthServices = "com.google.android.gms:play-services-auth:$googleServicesVersion"
		const val activityCompose = "androidx.activity:activity-compose:$activityComposeVersion"
		const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
	}
	
	object Compose{
		const val core = "androidx.compose.ui:ui:$composeVersion"
		const val tooling = "androidx.compose.ui:ui-tooling:$composeVersion"
		const val toolingPreview = "androidx.compose.ui:ui-tooling-preview:$composeVersion"
		const val foundation= "androidx.compose.foundation:foundation:$composeVersion"
		const val material = "androidx.compose.material:material:$composeVersion"
	}
	
	object Kotlin {
		const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinSerializationVersion"
	}
	
	object Coroutines {
		const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"
		const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"
	}
	
	object Kodein {
		const val android = "org.kodein.di:kodein-di-framework-android-x:$kodeinVersion"
		const val core = "org.kodein.di:kodein-di:$kodeinVersion"
	}
	
	object Ktor {
		const val clientCore = "io.ktor:ktor-client-core:$ktorVersion"
		const val clientOkHttp = "io.ktor:ktor-client-okhttp:$ktorVersion"
		const val clientDarwin = "io.ktor:ktor-client-darwin:$ktorVersion"
		const val clientContentNegotiation = "io.ktor:ktor-client-content-negotiation:$ktorVersion"
		const val clientLogging = "io.ktor:ktor-client-logging:$ktorVersion"
		const val kotlin_json = "io.ktor:ktor-serialization-kotlinx-json:$ktorVersion"
	}
	
	object SqlDelight {
		const val core = "app.cash.sqldelight:runtime:$sqlDelightVersion"
		const val androidDriver = "app.cash.sqldelight:android-driver:$sqlDelightVersion"
		const val nativeDriver = "app.cash.sqldelight:native-driver:$sqlDelightVersion"
	}
}
