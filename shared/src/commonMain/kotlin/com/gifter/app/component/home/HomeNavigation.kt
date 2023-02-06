package com.gifter.app.component.home

import com.gifter.app.component.BaseNavigation

class HomeNavigation(
	private val navigateRegistration: () -> Unit
) : BaseNavigation {
	fun navigateRegistration() {
		navigateRegistration.invoke()
	}
}