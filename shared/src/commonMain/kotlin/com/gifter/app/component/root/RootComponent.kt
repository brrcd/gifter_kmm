package com.gifter.app.component.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.gifter.app.component.signin.SignInComponent
import com.gifter.app.component.main.MainComponent
import com.gifter.app.component.root.Root.Child
import com.gifter.app.data.Repository
import com.gifter.app.di.PlatformModule
import com.gifter.app.di.PlatformModule.instance
import com.gifter.app.settings.DeviceSettings
import io.ktor.client.HttpClient
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerAuthConfig
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.bearerAuth
import org.kodein.di.DI
import org.kodein.di.DIAware

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
	
	private val repository = instance<Repository>()
	
	private val authToken: String = repository.getJWT()
	
	private val stack = childStack(
		source = navigation,
		initialConfiguration = if (authToken.isNotEmpty()) Configuration.SignIn
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

