package com.gifter.app.component.registration

import com.arkivanov.decompose.ComponentContext
import com.gifter.app.component.BaseChildComponent
import com.gifter.app.component.coroutineScope
import com.gifter.app.data.Repository
import com.gifter.app.data.remote.RequestResult
import com.gifter.app.di.PlatformModule.diInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class RegistrationComponent(
	componentContext: ComponentContext,
	override val navigation: RegistrationNavigation,
	override val onLoading: (Boolean) -> Unit,
	override val onError: (RequestResult.Error) -> Unit
) : BaseChildComponent, ComponentContext by componentContext {
	
	private val scope: CoroutineScope = coroutineScope()
	
	private val repository = diInstance<Repository>()
	
	fun registerUser(id: String, name: String) {
		scope.launch {
			showLoading()
			val result = repository.registerUser(id, name)
			hideLoading()
			when (result) {
				is RequestResult.Success -> {
					navigation.navigateMain()
				}
				is RequestResult.Error -> {
					displayError(result)
				}
			}
		}
	}
}