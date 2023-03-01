package com.gifter.component.main

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.gifter.component.BaseParentComponent
import com.gifter.component.home.HomeComponent
import com.gifter.component.profile.ProfileComponent
import com.gifter.component.wish.WishComponent


interface Main : BaseParentComponent {
	
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