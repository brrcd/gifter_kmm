package com.gifter.di.local

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.gifter.data.AppDatabase
import com.gifter.di.Platform
import com.gifter.di.util.DB_NAME

actual class DatabaseDriverFactory actual constructor(private val platform: Platform) {
	actual fun create(): SqlDriver =
		AndroidSqliteDriver(AppDatabase.Schema, platform.context, DB_NAME)
}