package com.gifter.app.di

import com.gifter.app.AppDatabase
import com.gifter.app.Platform
import com.gifter.app.data.local.DatabaseDriverFactory
import com.gifter.app.data.local.LocalSource
import com.gifter.app.data.local.LocalSourceImpl
import com.gifter.app.settings.DeviceSettings
import com.gifter.app.settings.SettingsFactory
import com.gifter.app.util.LOCAL_DATA_SOURCE_DI_MODULE
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

class LocalSourceModule: DIModule {
	override val module = DI.Module(name = LOCAL_DATA_SOURCE_DI_MODULE) {
		bind<AppDatabase>() with singleton {
			AppDatabase(DatabaseDriverFactory(instance()).create())
		}
		bind<DeviceSettings>() with singleton {
			DeviceSettings(SettingsFactory(instance()).create())
		}
		bind<LocalSource>() with singleton {
			LocalSourceImpl(instance(), instance())
		}
	}
}