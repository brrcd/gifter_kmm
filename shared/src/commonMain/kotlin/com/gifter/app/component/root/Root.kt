package com.gifter.app.component.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.gifter.app.component.signin.SignInComponent
import com.gifter.app.component.main.MainComponent

interface Root{
	
	val childStack: Value<ChildStack<*, Child>>
	
	sealed class Child{
		data class SignIn(val component: SignInComponent): Child()
		data class Main(val component: MainComponent): Child()
	}
}