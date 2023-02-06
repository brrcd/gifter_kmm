package com.gifter.app

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun runOnMain(block: () -> Unit) = withContext(Dispatchers.Main) { block.invoke() }