package com.gifter.app.component.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.navigate
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.Lifecycle
import com.arkivanov.essenty.lifecycle.LifecycleOwner
import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.gifter.app.component.coroutineScope
import com.gifter.app.component.signin.SignInComponent
import com.gifter.app.component.main.MainComponent
import com.gifter.app.component.root.Root.Child
import com.gifter.app.di.PlatformModule
import com.gifter.app.settings.DeviceSettings
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext

class RootComponent constructor(
	componentContext: ComponentContext,
	private val signInComponent: (ComponentContext) -> SignInComponent,
	private val mainComponent: (ComponentContext) -> MainComponent
) : Root, ComponentContext by componentContext {
	
	constructor(
		componentContext: ComponentContext,
	) : this(
		componentContext = componentContext,
		signInComponent = { childContext ->
			SignInComponent(
				componentContext = childContext,
			)
		},
		mainComponent = { childContext ->
			MainComponent(
				componentContext = childContext
			)
		}
	)
	
	private val navigation = StackNavigation<Configuration>()
	
	private val scope = coroutineScope(Dispatchers.Main + SupervisorJob())
	
	private val stack = childStack(
		source = navigation,
		initialConfiguration = Configuration.SignIn,
		handleBackButton = true,
		childFactory = ::createChild
	)
	
	override val childStack: Value<ChildStack<*, Child>> = stack
	
	private val settings = PlatformModule.instance<DeviceSettings>()
	
	private val authToken: String = settings.authToken
	
	init {
		if (authToken.isEmpty()) {
			navigation.push(Configuration.Main)
		} else {
			navigation.push(Configuration.SignIn)
		}
	}
	
	private fun createChild(
		configuration: Configuration,
		componentContext: ComponentContext
	): Child =
		when (configuration) {
			is Configuration.SignIn -> Child.SignIn(
				signInComponent(componentContext)
			)
			is Configuration.Main -> Child.Main(
				mainComponent(componentContext)
			)
		}
	
	private sealed class Configuration : Parcelable {
		
		@Parcelize
		object SignIn : Configuration()
		
		@Parcelize
		object Main : Configuration()
		
	}
}

