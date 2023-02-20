package com.gifter.app.component

import com.gifter.app.data.remote.RequestResult

interface BaseChildComponent {
	val onLoading: (Boolean) -> Unit
	val onError: (RequestResult.Error) -> Unit
	val navigation: BaseNavigation
	
	fun showLoading() {
		onLoading.invoke(true)
	}
	
	fun hideLoading() {
		onLoading.invoke(false)
	}
	
	fun displayError(error: RequestResult.Error) {
		onError.invoke(error)
	}
}