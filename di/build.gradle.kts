plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

kotlin {
    android()
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "di"
        }
    }
    
    sourceSets {
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val commonMain by getting {
            dependencies {
                implementation(project(":data"))
                implementation(Deps.Ktor.clientCore)
                implementation(Deps.Ktor.clientLogging)
                implementation(Deps.Ktor.clientContentNegotiation)
                implementation(Deps.Ktor.clientAuth)
                implementation(Deps.Ktor.kotlin_json)
                implementation(Deps.SqlDelight.core)
                implementation(Deps.Settings.core)
                implementation(Deps.Settings.noArgs)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Deps.Ktor.clientOkHttp)
                implementation(Deps.SqlDelight.androidDriver)
            }
        }
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation(Deps.Ktor.clientDarwin)
                implementation(Deps.SqlDelight.nativeDriver)
            }
        }
    }
}

android {
    namespace = "com.gifter.di"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
        targetSdk = 33
    }
}