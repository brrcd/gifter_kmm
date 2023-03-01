package com.gifter.component.registration

import com.arkivanov.decompose.ComponentContext
import com.gifter.component.BaseChildComponent
import com.gifter.component.coroutineScope
import com.gifter.data.Repository
import com.gifter.data.remote.RequestResult
import com.gifter.di.module.PlatformModule.diInstance
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