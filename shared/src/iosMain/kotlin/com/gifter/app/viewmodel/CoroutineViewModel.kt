package com.gifter.app.viewmodel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import org.kodein.di.DIAware

actual abstract class CoroutineViewModel actual constructor() {
	actual val coroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())
	
	actual fun dispose() {
		coroutineScope.cancel()
		onCleared()
	}
	
	protected actual open fun onCleared(){
	}
}