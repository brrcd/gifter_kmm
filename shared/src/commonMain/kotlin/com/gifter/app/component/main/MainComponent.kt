package com.gifter.app.component.main

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.gifter.app.component.home.HomeComponent
import com.gifter.app.component.home.HomeNavigation
import com.gifter.app.component.profile.ProfileComponent
import com.gifter.app.component.root.Root
import com.gifter.app.data.Repository
import com.gifter.app.di.PlatformModule.instance

class MainComponent(componentContext: ComponentContext, val rootComponent: Root) : Main,
	ComponentContext by componentContext {
	
	private val navigation = StackNavigation<MainConfig>()
	
	private val repository = instance<Repository>()
	
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
					)
				)
			)
			is MainConfig.Profile -> Main.Child.Profile(
				ProfileComponent(
					componentContext = componentContext
				)
			)
		}
	
	override fun onHomeTabSelected() {
		navigation.bringToFront(MainConfig.Home)
	}
	
	override fun onProfileTabSelected() {
		navigation.bringToFront(MainConfig.Profile)
	}
	
	private sealed interface MainConfig : Parcelable {
		@Parcelize
		object Home : MainConfig
		
		@Parcelize
		object Profile : MainConfig
	}
}