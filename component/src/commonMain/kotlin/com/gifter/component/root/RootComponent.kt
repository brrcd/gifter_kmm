package com.gifter.component.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.gifter.component.BaseParentComponentImpl
import com.gifter.component.coroutineScope
import com.gifter.component.main.MainComponent
import com.gifter.component.registration.RegistrationComponent
import com.gifter.component.registration.RegistrationNavigation
import com.gifter.component.root.Root.Child
import com.gifter.component.runOnMain
import com.gifter.component.signin.SignInComponent
import com.gifter.component.signin.SignInNavigation
import com.gifter.di.module.ServiceContainer
import kotlinx.coroutines.launch

class RootComponent constructor(
	componentContext: ComponentContext
) : Root, BaseParentComponentImpl(componentContext){

	private val navigation = StackNavigation<Configuration>()
	
	private val repository = ServiceContainer.repository
	
	private val scope = coroutineScope()
	
	private val stack = childStack(
		source = navigation,
		initialConfiguration =
		if (repository.getAuthToken().isEmpty().also { println("authToken is empty? =$it") }) Configuration.SignIn
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
					navigation = SignInNavigation(
						navigateMain = { navigateMain() },
						navigateRegistration = { navigateRegistration() }
					),
					onError = ::onError,
					onLoading = ::onLoading
				)
			)
			is Configuration.Main -> Child.Main(
				MainComponent(
					componentContext = componentContext,
					rootComponent = this
				)
			)
			is Configuration.Registration -> Child.Registration(
				RegistrationComponent(
					componentContext = componentContext,
					navigation = RegistrationNavigation(
						navigateMain = { navigateMain() }
					),
					onError = ::onError,
					onLoading = ::onLoading
				)
			)
		}
	
	override fun navigateRegistration() {
		scope.launch {
			runOnMain {
				navigation.push(Configuration.Registration)
			}
		}
	}
	
	override fun navigateMain() {
		scope.launch {
			runOnMain {
				navigation.push(Configuration.Main)
			}
		}
	}
	
	override fun navigateSignIn() {
		scope.launch {
			runOnMain {
				navigation.push(Configuration.SignIn)
			}
		}
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
