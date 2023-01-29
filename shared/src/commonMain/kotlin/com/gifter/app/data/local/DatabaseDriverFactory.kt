package com.gifter.app.data.local

import app.cash.sqldelight.db.SqlDriver

expect class DatabaseDriverFactory {
	fun create(): SqlDriver
}