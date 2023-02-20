package com.gifter.app

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend inline fun runOnMain(crossinline block: () -> Unit) = withContext(Dispatchers.Main) { block.invoke() }