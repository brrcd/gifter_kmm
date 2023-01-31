package com.gifter.app.component.signin

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.gifter.app.component.registration.RegistrationComponent

interface SignIn {
	
	val childStack: Value<ChildStack<*, Child>>
	
	sealed class Child{
		data class Registration(val component: RegistrationComponent): Child()
	}
}