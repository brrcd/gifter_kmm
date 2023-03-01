package com.gifter.component.signin

import com.gifter.component.BaseNavigation

class SignInNavigation(
	private val navigateMain: () -> Unit,
	private val navigateRegistration: () -> Unit
): BaseNavigation {
	fun navigateMain(){
		navigateMain.invoke()
	}
	fun navigateRegistration(){
		navigateRegistration.invoke()
	}
}