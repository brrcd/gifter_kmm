package com.gifter.app.viewmodel

import kotlinx.coroutines.CoroutineScope
import org.kodein.di.DIAware

expect abstract class CoroutineViewModel constructor() {
	val coroutineScope: CoroutineScope
	
	fun dispose()
	
	protected open fun onCleared()
}