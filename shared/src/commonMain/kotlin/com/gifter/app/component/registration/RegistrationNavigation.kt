package com.gifter.app.component.registration

import com.gifter.app.component.BaseNavigation

class RegistrationNavigation(private val navigateMain: () -> Unit) : BaseNavigation {
	fun navigateMain() {
		navigateMain.invoke()
	}
}