package com.gifter.app.data.local

import app.cash.sqldelight.db.SqlDriver
import com.gifter.app.Platform

expect class DatabaseDriverFactory constructor(platform: Platform) {
	fun create(): SqlDriver
}