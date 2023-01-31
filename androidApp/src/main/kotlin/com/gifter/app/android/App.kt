package com.gifter.app.android

import android.app.Application
import com.gifter.app.Platform
import com.gifter.app.di.PlatformModule
import com.gifter.app.di.RemoteSourceModule
import org.kodein.di.DI
import org.kodein.di.DIAware

class App: Application() {
	
	override fun onCreate() {
		super.onCreate()
		PlatformModule.init(Platform(this))
	}
}