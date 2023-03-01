package com.gifter.app.android

import android.app.Application
import com.gifter.di.Platform
import com.gifter.di.module.PlatformModule

class App: Application() {
	
	override fun onCreate() {
		super.onCreate()
		PlatformModule.init(Platform(this))
	}
}