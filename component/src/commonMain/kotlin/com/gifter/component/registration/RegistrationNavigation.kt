package com.gifter.component.registration

import com.gifter.component.BaseNavigation

class RegistrationNavigation(private val navigateMain: () -> Unit) : BaseNavigation {
	fun navigateMain() {
		navigateMain.invoke()
	}
}