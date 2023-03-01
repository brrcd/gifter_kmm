package com.gifter.di.module

import com.gifter.data.AppDatabase
import com.gifter.data.local.DeviceSettings
import com.gifter.data.local.LocalSource
import com.gifter.data.local.LocalSourceImpl
import com.gifter.di.local.DatabaseDriverFactory
import com.gifter.di.settings.SettingsFactory
import com.gifter.di.util.LOCAL_DATA_SOURCE_DI_MODULE
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
			LocalSourceImpl(
				instance(),
				instance()
			)
		}
	}
}