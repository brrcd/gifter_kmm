package com.gifter.app.component.main

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.gifter.app.component.BaseParentComponent
import com.gifter.app.component.home.HomeComponent
import com.gifter.app.component.profile.ProfileComponent
import com.gifter.app.component.wish.WishComponent
import com.gifter.app.data.remote.RequestResult


interface Main : BaseParentComponent{
	
	val childStack: Value<ChildStack<*, Child>>
	
	fun onHomeTabSelected()
	fun onProfileTabSelected()
	fun onWishTabSelected()
	
	sealed interface Child {
		data class Home(val component: HomeComponent) : Child
		data class Profile(val component: ProfileComponent): Child
		data class Wish(val component: WishComponent): Child
	}
}