import java.util.Properties

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("plugin.serialization")
}

android {
    namespace = "com.gifter.app.android"
    compileSdk = 33
    signingConfigs {
        getByName("debug") {
            val props = File (rootDir, "keystore.properties").inputStream().use {
                Properties().apply { load(it) }
            }
            storeFile = file(props.getValue("storeFile") as String)
            keyAlias = props.getValue("keyAlias") as String
            keyPassword = props.getValue("keyPassword") as String
            storePassword = props.getValue("storePassword") as String
        }
    }
    defaultConfig {
        applicationId = "com.gifter.app.android"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        val gOauthProps = File (rootDir, "google_oauth.properties").inputStream().use {
            Properties().apply { load(it) }
        }
        buildConfigField("String", "SERVER_CLIENT_ID", gOauthProps.getValue("server_client_id") as String)
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.0"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            isDebuggable = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"),
            "proguard-rules.pro")
        }
        getByName("debug") {
            isDebuggable = true
            isMinifyEnabled = false
            isShrinkResources = false
        }
        create("staging") {
            isDebuggable = true
        }
    }
}

dependencies {
    implementation(project(":shared"))
    
    implementation(Deps.Compose.core)
    implementation(Deps.Compose.foundation)
    implementation(Deps.Compose.tooling)
    implementation(Deps.Compose.material)
    implementation(Deps.Compose.toolingPreview)
    
    implementation(Deps.Android.activityCompose)
    implementation(Deps.Android.googleAuthServices)
    implementation(Deps.Android.composeNavigation)
    
    implementation(Deps.MVIKotlin.mviKotlin)
    implementation(Deps.MVIKotlin.mviKotlinMain)
    
    implementation(Deps.Decompose.decompose)
    implementation(Deps.Decompose.extensionCompose)
    
    implementation(Deps.Essenty.core)
    
    implementation(Deps.Kotlin.serialization)
    implementation(Deps.Coroutines.android)
    
    implementation(Deps.Kodein.android)
    implementation(Deps.Kodein.core)
}