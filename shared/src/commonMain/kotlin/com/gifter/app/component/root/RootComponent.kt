package com.gifter.app.component.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.overlay.ChildOverlay
import com.arkivanov.decompose.router.overlay.OverlayNavigation
import com.arkivanov.decompose.router.overlay.activate
import com.arkivanov.decompose.router.overlay.childOverlay
import com.arkivanov.decompose.router.overlay.dismiss
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.gifter.app.component.coroutineScope
import com.gifter.app.component.infopopup.PopupComponent
import com.gifter.app.component.loading.LoadingComponent
import com.gifter.app.component.signin.SignInComponent
import com.gifter.app.component.main.MainComponent
import com.gifter.app.component.registration.RegistrationComponent
import com.gifter.app.component.registration.RegistrationNavigation
import com.gifter.app.component.root.Root.Child
import com.gifter.app.component.signin.SignInNavigation
import com.gifter.app.data.Repository
import com.gifter.app.data.remote.RequestResult
import com.gifter.app.di.PlatformModule.instance
import com.gifter.app.runOnMain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RootComponent constructor(
	componentContext: ComponentContext
) : Root, ComponentContext by componentContext {
	
	private val popUpNavigation = OverlayNavigation<PopUpConfig>()
	private val loadingNavigation = OverlayNavigation<Loading>()
	private val navigation = StackNavigation<Configuration>()
	
	private val scope = coroutineScope(Dispatchers.Default + SupervisorJob())
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
	
	private val popupChild = childOverlay(
		source = popUpNavigation,
		key = "popUp",
		handleBackButton = true
	) { config, componentContext ->
		PopupComponent(
			componentContext = componentContext,
			config.message
		)
	}
	
	override val popupOverlay: Value<ChildOverlay<*, PopupComponent>> = popupChild
	override val loadingOverlay: Value<ChildOverlay<*, LoadingComponent>> =
		childOverlay(
			source = loadingNavigation,
			key = "loading"
		) { _, _ -> LoadingComponent() }
	
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
		navigation.push(Configuration.Registration)
	}
	
	override fun navigateMain() {
		navigation.push(Configuration.Main)
	}
	
	override fun navigateSignIn() {
		navigation.push(Configuration.SignIn)
	}
	
	override fun onLoading(isLoading: Boolean) {
		scope.launch {
			runOnMain {
				if (isLoading) loadingNavigation.activate(Loading)
				else loadingNavigation.dismiss()
			}
		}
	}
	
	override fun onError(result: RequestResult.Error) {
		scope.launch {
			runOnMain {
				val config = PopUpConfig(result.error.errorMessage)
				popUpNavigation.activate(config)
			}
			withContext(Dispatchers.Default) {
				delay(5000)
			}
			runOnMain {
				popUpNavigation.dismiss()
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
	
	@Parcelize
	private data class PopUpConfig(
		val message: String
	) : Parcelable
	
	@Parcelize
	private object Loading : Parcelable
}
