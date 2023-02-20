package com.gifter.app.component.signin

import com.arkivanov.decompose.ComponentContext
import com.gifter.app.component.BaseChildComponent
import com.gifter.app.component.coroutineScope
import com.gifter.app.data.Repository
import com.gifter.app.data.remote.RequestResult
import com.gifter.app.di.PlatformModule.diInstance
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