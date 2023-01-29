package com.gifter.app.di

import com.gifter.app.util.LOCAL_DATA_SOURCE_DI_MODULE
import org.kodein.di.DI

class LocalSourceModule: DIModule {
	override val module = DI.Module(name = LOCAL_DATA_SOURCE_DI_MODULE) {
//		bind<AppDatabase>() with singleton {
//			AppDatabase(DatabaseDriverFactory().create())
//		}
//		bind<LocalSource>() with singleton {
//			LocalSourceImpl(instance())
//		}
	}
}