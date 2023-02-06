package com.gifter.app.component.signin

import com.arkivanov.decompose.ComponentContext
import com.gifter.app.component.BaseChildComponent
import com.gifter.app.component.BaseNavigation
import com.gifter.app.component.coroutineScope
import com.gifter.app.data.Repository
import com.gifter.app.data.remote.RequestResult
import com.gifter.app.di.PlatformModule
import com.gifter.app.di.PlatformModule.instance
import com.gifter.app.runOnMain
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignInComponent(
	componentContext: ComponentContext,
	override val navigation: SignInNavigation,
	override val onError: (RequestResult.Error) -> Unit,
	override val onLoading: (Boolean) -> Unit
) : BaseChildComponent(), ComponentContext by componentContext {
	
	private val scope = coroutineScope()
	private val repository = instance<Repository>()
	
	fun verifyToken(token: String) {
		scope.launch {
			when (val result = repository.verifyGoogleIdToken(token)){
				is RequestResult.Success -> {
					getUser()
				}
				is RequestResult.Error -> {
					displayError(result)
				}
			}
		}
	}
	
	private fun getUser() {
		scope.launch {
			showLoading()
			val result = repository.getUser()
			hideLoading()
			when (result){
				is RequestResult.Success -> {
					runOnMain {
//						if (result.data.isRegistrationComplete) {
//							navigation.navigateMain()
//						} else {
							navigation.navigateRegistration()
//						}
					}
				}
				is RequestResult.Error -> {
					displayError(result)
				}
			}
		}
	}
}