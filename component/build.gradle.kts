plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("kotlin-parcelize")
}

kotlin {
    android()
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "component"
        }
    }
    
    sourceSets {
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val commonMain by getting {
            dependencies {
                implementation(project(":data"))
                implementation(project(":di"))
                implementation(Deps.Decompose.decompose)
                implementation(Deps.Coroutines.core)
            }
        }
        val androidMain by getting {
            dependencies {
            }
        }
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
            }
        }
    }
}

android {
    namespace = "com.gifter.component"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
        targetSdk = 33
    }
}