package com.gifter.app.di

import com.gifter.app.Platform
import org.kodein.di.DI
import org.kodein.di.DirectDI
import org.kodein.di.bind
import org.kodein.di.direct
import org.kodein.di.instance
import org.kodein.di.singleton
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object PlatformModule {
	
	private var _di: DirectDI? = null
	val di get(): DirectDI = requireNotNull(_di)
	
	fun init(platform: Platform) {
		val platformModule = DI.Module("platform_module") {
			bind<Platform>() with singleton { platform }
		}
		_di = DI {
			importAll(
				platformModule,
				RepositoryModule().module
			)
		}.direct
	}
	
	inline fun <reified T> diInstance(): T {
		return di.instance()
	}
}