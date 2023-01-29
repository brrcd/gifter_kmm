package com.gifter.app

import android.content.Context

actual class Platform constructor(private val context: Context) {
    fun getContext() = context
}