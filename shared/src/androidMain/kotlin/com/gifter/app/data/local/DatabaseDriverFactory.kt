package com.gifter.app.data.local

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.gifter.app.AppDatabase
import com.gifter.app.Platform
import com.gifter.app.util.DB_NAME

actual class DatabaseDriverFactory actual constructor(private val platform: Platform) {
	actual fun create(): SqlDriver =
		AndroidSqliteDriver(AppDatabase.Schema, platform.context, DB_NAME)
}