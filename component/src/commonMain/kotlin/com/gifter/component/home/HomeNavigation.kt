package com.gifter.component.home

import com.gifter.component.BaseNavigation

class HomeNavigation(
	private val navigateRegistration: () -> Unit
) : BaseNavigation {
	fun navigateRegistration() {
		navigateRegistration.invoke()
	}
}