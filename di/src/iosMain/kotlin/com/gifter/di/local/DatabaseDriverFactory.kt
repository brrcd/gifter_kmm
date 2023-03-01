package com.gifter.di.local

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.gifter.data.AppDatabase
import com.gifter.di.Platform
import com.gifter.di.util.DB_NAME

actual class DatabaseDriverFactory actual constructor(platform: Platform){
	actual fun create(): SqlDriver = NativeSqliteDriver(AppDatabase.Schema, DB_NAME)
}