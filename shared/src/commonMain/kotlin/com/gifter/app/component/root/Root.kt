package com.gifter.app.component.root

import com.arkivanov.decompose.router.overlay.ChildOverlay
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.gifter.app.component.infopopup.PopupComponent
import com.gifter.app.component.loading.LoadingComponent
import com.gifter.app.component.signin.SignInComponent
import com.gifter.app.component.main.MainComponent
import com.gifter.app.component.registration.RegistrationComponent
import com.gifter.app.data.remote.RequestResult
import kotlinx.coroutines.flow.StateFlow

interface Root {
	
	val popupOverlay: Value<ChildOverlay<*, PopupComponent>>
	val loadingOverlay: Value<ChildOverlay<*, LoadingComponent>>
	
	val childStack: Value<ChildStack<*, Child>>
	
	fun navigateRegistration()
	fun navigateMain()
	fun navigateSignIn()
	fun onError(result: RequestResult.Error)
	fun onLoading(isLoading: Boolean)
	
	sealed interface Child{
		data class SignIn(val component: SignInComponent): Child
		data class Registration(val component: RegistrationComponent): Child
		data class Main(val component: MainComponent): Child
	}
}