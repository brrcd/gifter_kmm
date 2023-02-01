plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("com.android.library")
    id("kotlin-parcelize")
    id("app.cash.sqldelight")
}

kotlin {
    android()
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val commonMain by getting {
            dependencies {
                implementation(Deps.Ktor.clientCore)
                implementation(Deps.Ktor.clientContentNegotiation)
                implementation(Deps.Ktor.kotlin_json)
                implementation(Deps.Ktor.clientAuth)
                implementation(Deps.Ktor.clientLogging)
                implementation(Deps.Kotlin.serialization)
                implementation(Deps.Coroutines.core)
                implementation(Deps.Kodein.core)
                implementation(Deps.SqlDelight.core)
                implementation(Deps.MVIKotlin.mviKotlin)
                implementation(Deps.Decompose.decompose)
                implementation(Deps.Settings.core)
                implementation(Deps.Settings.noArgs)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Deps.Ktor.clientOkHttp)
                implementation(Deps.SqlDelight.androidDriver)
                implementation(Deps.Android.viewModel)
                implementation(Deps.MVIKotlin.mviKotlin)
                implementation(Deps.Decompose.decompose)
                implementation(Deps.Essenty.core)
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
//        val commonTest by getting {
//            dependencies {
//                implementation(kotlin("test"))
//            }
//        }
        // says there is no such source set, could be a bug, test later
//        val androidTest by getting
//        val iosX64Test by getting
//        val iosArm64Test by getting
//        val iosSimulatorArm64Test by getting
//        val iosTest by creating {
//            dependsOn(commonTest)
//            iosX64Test.dependsOn(this)
//            iosArm64Test.dependsOn(this)
//            iosSimulatorArm64Test.dependsOn(this)
//        }
    }
}

android {
    namespace = "com.gifter.app"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
        targetSdk = 33
    }
}

sqldelight {
    databases {
        create("AppDatabase") {
            packageName.set("com.gifter.app")
        }
    }
}