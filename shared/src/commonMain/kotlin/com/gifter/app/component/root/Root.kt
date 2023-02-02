package com.gifter.app.component.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.gifter.app.component.signin.SignInComponent
import com.gifter.app.component.main.MainComponent
import com.gifter.app.component.registration.RegistrationComponent

interface Root {
	
	val childStack: Value<ChildStack<*, Child>>
	
	fun navigateRegistration()
	fun navigateMain()
	fun navigateSignIn()
	
	sealed class Child{
		data class SignIn(val component: SignInComponent): Child()
		data class Registration(val component: RegistrationComponent): Child()
		data class Main(val component: MainComponent): Child()
	}
}