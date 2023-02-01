package com.gifter.app.component.signin

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.gifter.app.component.coroutineScope
import com.gifter.app.component.registration.RegistrationComponent
import com.gifter.app.data.Repository
import com.gifter.app.data.remote.RequestResult
import com.gifter.app.di.PlatformModule
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.bearerAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.kodein.di.DI
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
	
	private val scope = coroutineScope(Dispatchers.Default + SupervisorJob())
	
	private val navigation = StackNavigation<Configuration>()
	
	private val repository: Repository = PlatformModule.instance()
	
	fun verifyToken(token: String) {
		scope.launch {
			when (val result = repository.verifyGoogleIdToken(token)) {
				is RequestResult.Success -> {
					repository.registerUser("name")
//					navigation.push(Configuration.Registration)
				}
				is RequestResult.Error -> {
					println("${result.error.errorCode} ${result.error.errorMessage}")
				}
			}
		}
	}
	
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
	
	private sealed class Configuration : Parcelable {
		@Parcelize
		object Registration : Configuration()
	}
}