package com.gifter.app.component.main

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.gifter.app.component.BaseParentComponentImpl
import com.gifter.app.component.coroutineScope
import com.gifter.app.component.home.HomeComponent
import com.gifter.app.component.home.HomeNavigation
import com.gifter.app.component.profile.ProfileComponent
import com.gifter.app.component.root.Root
import com.gifter.app.component.wish.WishComponent
import com.gifter.app.component.wish.WishNavigation
import com.gifter.app.data.Repository
import com.gifter.app.di.PlatformModule.diInstance
import com.gifter.app.runOnMain
import kotlinx.coroutines.launch

class MainComponent(componentContext: ComponentContext, val rootComponent: Root) : Main,
	BaseParentComponentImpl(componentContext) {
	
	private val navigation = StackNavigation<MainConfig>()
	
	private val repository = diInstance<Repository>()
	
	private val scope = coroutineScope()
	
	private val stack = childStack(
		source = navigation,
		initialConfiguration = MainConfig.Home,
		handleBackButton = true,
		childFactory = ::createChild
	)
	
	override val childStack: Value<ChildStack<*, Main.Child>> = stack
	
	private fun createChild(
		configuration: MainConfig,
		componentContext: ComponentContext
	) =
		when (configuration) {
			is MainConfig.Home -> Main.Child.Home(
				HomeComponent(
					componentContext = componentContext,
					navigation = HomeNavigation(
						navigateRegistration = { rootComponent.navigateRegistration() }
					),
					onError = ::onError,
					onLoading = ::onLoading
				)
			)
			is MainConfig.Profile -> Main.Child.Profile(
				ProfileComponent(
					componentContext = componentContext
				)
			)
			is MainConfig.Wish -> Main.Child.Wish(
				WishComponent(
					componentContext = componentContext,
					navigation = WishNavigation(
					
					),
					onError = ::onError,
					onLoading = ::onLoading
				)
			)
		}
	
	override fun onHomeTabSelected() {
		scope.launch {
			runOnMain {
				navigation.bringToFront(MainConfig.Home)
			}
		}
	}
	
	override fun onProfileTabSelected() {
		scope.launch {
			runOnMain {
				navigation.bringToFront(MainConfig.Profile)
			}
		}
	}
	
	override fun onWishTabSelected() {
		scope.launch {
			runOnMain {
				navigation.bringToFront(MainConfig.Wish)
			}
		}
	}
	
	private sealed interface MainConfig : Parcelable {
		@Parcelize
		object Home : MainConfig
		
		@Parcelize
		object Profile : MainConfig
		
		@Parcelize
		object Wish: MainConfig
	}
}