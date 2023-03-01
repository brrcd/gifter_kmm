package com.gifter.di.module

import com.gifter.data.Repository
import com.gifter.data.RepositoryImpl
import com.gifter.di.util.REPOSITORY_DI_MODULE
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

class RepositoryModule: DIModule {
	override val module = DI.Module(REPOSITORY_DI_MODULE) {
		import(RemoteSourceModule().module)
		import(LocalSourceModule().module)
		bind<Repository>() with singleton {
			RepositoryImpl(
				instance(),
				instance()
			)
		}
	}
}