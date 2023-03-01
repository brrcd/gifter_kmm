package com.gifter.di.module

import com.gifter.data.Repository
import com.gifter.data.RepositoryImpl
import com.gifter.di.Platform

internal class RepositoryModule(platform: Platform) {
	
	private val localSourceModule = LocalSourceModule(platform)
	private val remoteSourceModule = RemoteSourceModule(localSourceModule.settings)
	val repository: Repository = RepositoryImpl(localSourceModule.localSource, remoteSourceModule.remoteSource)
}