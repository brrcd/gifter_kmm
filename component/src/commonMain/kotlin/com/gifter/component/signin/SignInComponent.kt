package com.gifter.component.signin

import com.arkivanov.decompose.ComponentContext
import com.gifter.component.BaseChildComponent
import com.gifter.component.coroutineScope
import com.gifter.data.Repository
import com.gifter.data.remote.RequestResult
import com.gifter.di.module.PlatformModule.diInstance
import kotlinx.coroutines.launch

class SignInComponent(
	componentContext: ComponentContext,
	override val navigation: SignInNavigation,
	override val onError: (RequestResult.Error) -> Unit,
	override val onLoading: (Boolean) -> Unit
) : BaseChildComponent, ComponentContext by componentContext {
	
	private val scope = coroutineScope()
	private val repository = diInstance<Repository>()
	
	fun verifyToken(token: String) {
		scope.launch {
			when (val result = repository.verifyGoogleIdToken(token)) {
				is RequestResult.Success -> {
					if (result.data.authToken.isNotEmpty()) {
						navigation.navigateMain()
					} else if (result.data.registrationToken.isNotEmpty()) {
						navigation.navigateRegistration()
					}
				}
				is RequestResult.Error -> {
					displayError(result)
				}
			}
		}
	}
}