package com.gifter.app.component

import com.gifter.app.data.remote.RequestResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BaseComponent {
	
	abstract val scope: CoroutineScope
	
	inline fun <reified T> apiCall(
		crossinline call: suspend () -> RequestResult<T>?,
		crossinline onSuccess: (RequestResult.Success<T>) -> Unit,
		crossinline onError: (RequestResult.Error) -> Unit
	) {
		scope.launch {
			when (val result = call.invoke()) {
				is RequestResult.Success -> {
					withContext(Dispatchers.Main) {
						onSuccess.invoke(result)
					}
				}
				is RequestResult.Error -> {
					withContext(Dispatchers.Main) {
						onError.invoke(result)
					}
				}
				else -> {
					withContext(Dispatchers.Main) {
						println("Api call else branch at base component")
					}
				}
			}
		}
	}
}