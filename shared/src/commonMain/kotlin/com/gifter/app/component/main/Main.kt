package com.gifter.app.component.main

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.gifter.app.component.home.HomeComponent
import com.gifter.app.component.profile.ProfileComponent
import com.gifter.app.data.remote.RequestResult


interface Main {
	
	val childStack: Value<ChildStack<*, Child>>
	
	fun onHomeTabSelected()
	fun onProfileTabSelected()
	
	sealed interface Child {
		data class Home(val component: HomeComponent) : Child
		data class Profile(val component: ProfileComponent): Child
	}
}