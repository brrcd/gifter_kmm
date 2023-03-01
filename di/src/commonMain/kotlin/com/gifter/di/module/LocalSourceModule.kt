package com.gifter.di.module

import com.gifter.data.AppDatabase
import com.gifter.data.local.DeviceSettings
import com.gifter.data.local.LocalSource
import com.gifter.data.local.LocalSourceImpl
import com.gifter.di.Platform
import com.gifter.di.local.DatabaseDriverFactory
import com.gifter.di.settings.SettingsFactory

internal class LocalSourceModule(platform: Platform) {
	private val database = AppDatabase(DatabaseDriverFactory(platform).create())
	val settings = DeviceSettings(SettingsFactory(platform).create())
	val localSource: LocalSource = LocalSourceImpl(database, settings)
}