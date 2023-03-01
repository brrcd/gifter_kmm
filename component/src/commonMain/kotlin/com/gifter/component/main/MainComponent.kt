package com.gifter.component.main

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.gifter.component.BaseParentComponentImpl
import com.gifter.component.coroutineScope
import com.gifter.component.home.HomeComponent
import com.gifter.component.home.HomeNavigation
import com.gifter.component.profile.ProfileComponent
import com.gifter.component.root.Root
import com.gifter.component.runOnMain
import com.gifter.component.wish.WishComponent
import com.gifter.component.wish.WishNavigation
import com.gifter.di.module.ServiceContainer
import kotlinx.coroutines.launch

class MainComponent(componentContext: ComponentContext, val rootComponent: Root) : Main,
	BaseParentComponentImpl(componentContext) {
	
	private val navigation = StackNavigation<MainConfig>()
	
	private val repository = ServiceContainer.repository
	
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