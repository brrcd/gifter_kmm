package com.gifter.app.component.home

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.gifter.app.component.BaseChildComponent
import com.gifter.app.component.coroutineScope
import com.gifter.app.data.Repository
import com.gifter.app.data.model.response.User
import com.gifter.app.data.remote.RequestResult
import com.gifter.app.di.PlatformModule
import com.gifter.app.runOnMain
import kotlinx.coroutines.launch

class HomeComponent(
	val componentContext: ComponentContext,
	override val navigation: HomeNavigation,
	override val onError: (RequestResult.Error) -> Unit,
	override val onLoading: (Boolean) -> Unit
) : BaseChildComponent, ComponentContext by componentContext {
	
	private val scope = coroutineScope()
	
	private val repository: Repository = PlatformModule.diInstance()
	
	private val _user = MutableValue(User("",""))
	val user: Value<User> get() = _user
	
	init {
		scope.launch {
			showLoading()
			val result = repository.getUser()
			hideLoading()
			when (result){
				is RequestResult.Success -> {
					runOnMain {
						_user.value = result.data
					}
				}
				is RequestResult.Error -> {
					displayError(result)
				}
			}
		}
	}
	
	// TODO REMOVE
	fun clearJWT(){
		repository.removeJWT()
	}
}