package com.gifter.component.home

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.gifter.component.BaseChildComponent
import com.gifter.component.coroutineScope
import com.gifter.component.runOnMain
import com.gifter.data.Repository
import com.gifter.data.model.response.User
import com.gifter.data.remote.RequestResult
import com.gifter.di.module.PlatformModule
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