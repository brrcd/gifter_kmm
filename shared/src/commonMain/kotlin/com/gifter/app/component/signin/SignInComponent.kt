package com.gifter.app.component.signin

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.gifter.app.component.registration.RegistrationComponent
import kotlin.coroutines.CoroutineContext

class SignInComponent(
	componentContext: ComponentContext,
	private val registrationComponent: (ComponentContext) -> RegistrationComponent
) : SignIn, ComponentContext by componentContext {
	
	constructor(
		componentContext: ComponentContext
	) : this(
		componentContext = componentContext,
		registrationComponent = { childContext ->
			RegistrationComponent(
				componentContext = childContext
			)
		}
	)
	
	private val navigation = StackNavigation<Configuration>()
	
	private val stack = childStack(
		source = navigation,
		initialConfiguration = Configuration.Registration,
		handleBackButton = true,
		childFactory = ::createChild
	)
	
	override val childStack: Value<ChildStack<*, SignIn.Child>> = stack
	
	private fun createChild(
		configuration: Configuration,
		componentContext: ComponentContext
	): SignIn.Child = SignIn.Child.Registration(
		registrationComponent(componentContext)
	)

	private sealed class Configuration: Parcelable {
		@Parcelize
		object Registration: Configuration()
	}
}