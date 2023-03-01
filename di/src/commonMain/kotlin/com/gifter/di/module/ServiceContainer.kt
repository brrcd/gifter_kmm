package com.gifter.di.module

import com.gifter.data.Repository
import com.gifter.di.Platform
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object ServiceContainer {
	
	lateinit var repository: Repository
	
	fun init(platform: Platform) {
		repository = RepositoryModule(platform).repository
	}
}