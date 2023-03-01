package com.gifter.di.local

import app.cash.sqldelight.db.SqlDriver
import com.gifter.di.Platform

expect class DatabaseDriverFactory constructor(platform: Platform) {
	fun create(): SqlDriver
}