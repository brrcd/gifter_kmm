package com.gifter.app.data.local

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.gifter.app.AppDatabase
import com.gifter.app.Platform
import com.gifter.app.util.DB_NAME

actual class DatabaseDriverFactory actual constructor(platform: Platform){
	actual fun create(): SqlDriver =
		NativeSqliteDriver(AppDatabase.Schema, DB_NAME)
}