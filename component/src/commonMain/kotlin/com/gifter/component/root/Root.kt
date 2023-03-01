package com.gifter.component.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.gifter.component.BaseParentComponent
import com.gifter.component.signin.SignInComponent
import com.gifter.component.main.MainComponent
import com.gifter.component.registration.RegistrationComponent

interface Root : BaseParentComponent {
	
	val childStack: Value<ChildStack<*, Child>>
	
	fun navigateRegistration()
	fun navigateMain()
	fun navigateSignIn()
	
	sealed interface Child{
		data class SignIn(val component: SignInComponent): Child
		data class Registration(val component: RegistrationComponent): Child
		data class Main(val component: MainComponent): Child
	}
}