package com.gifter.app.component

import com.gifter.app.data.remote.RequestResult

abstract class BaseChildComponent {
	abstract val onLoading: (Boolean) -> Unit
	abstract val onError: (RequestResult.Error) -> Unit
	abstract val navigation: BaseNavigation
	
	fun showLoading(){
		onLoading.invoke(true)
	}
	
	fun hideLoading(){
		onLoading.invoke(false)
	}
	
	fun displayError(error: RequestResult.Error){
		onError.invoke(error)
	}
}