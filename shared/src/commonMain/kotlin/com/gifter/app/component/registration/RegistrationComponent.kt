package com.gifter.app.component.registration

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.gifter.app.component.BaseComponent
import com.gifter.app.component.coroutineScope
import com.gifter.app.component.main.MainComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class RegistrationComponent(
	componentContext: ComponentContext,
) : BaseComponent(), ComponentContext by componentContext {
	
	override val scope: CoroutineScope
		get() = coroutineScope(Dispatchers.Default + SupervisorJob())
	
}