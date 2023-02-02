package com.gifter.app.component.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.gifter.app.component.signin.SignInComponent
import com.gifter.app.component.main.MainComponent
import com.gifter.app.component.registration.RegistrationComponent
import com.gifter.app.component.root.Root.Child
import com.gifter.app.data.Repository
import com.gifter.app.di.PlatformModule.instance

class RootComponent constructor(
	componentContext: ComponentContext
) : Root, ComponentContext by componentContext {
	
	private val navigation = StackNavigation<Configuration>()
	
	private val repository = instance<Repository>()
	
	private val authToken: String = repository.getJWT()
	
	private val stack = childStack(
		source = navigation,
		initialConfiguration =
			if (authToken.isEmpty()) Configuration.SignIn
			else Configuration.Main,
		handleBackButton = true,
		childFactory = ::createChild
	)
	
	override val childStack: Value<ChildStack<*, Child>> = stack
	
	private fun createChild(
		configuration: Configuration,
		componentContext: ComponentContext
	): Child =
		when (configuration) {
			is Configuration.SignIn -> Child.SignIn(
				SignInComponent(
					componentContext = componentContext,
					navigateRegistration = ::navigateRegistration,
					navigateMain = ::navigateMain
				)
			)
			is Configuration.Main -> Child.Main(
				MainComponent(
					componentContext = componentContext
				)
			)
			is Configuration.Registration -> Child.Registration(
				RegistrationComponent(
					componentContext = componentContext,
					navigateMain = ::navigateMain
				)
			)
		}
	
	override fun navigateRegistration() {
		navigation.push(Configuration.Registration)
	}
	
	override fun navigateMain() {
		navigation.push(Configuration.Main)
	}
	
	override fun navigateSignIn() {
		navigation.push(Configuration.SignIn)
	}
	
	private sealed interface Configuration : Parcelable {
		@Parcelize
		object SignIn : Configuration
		
		@Parcelize
		object Registration : Configuration
		
		@Parcelize
		object Main : Configuration
	}
}

