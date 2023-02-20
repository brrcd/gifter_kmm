package com.gifter.app.component.wish

import com.arkivanov.decompose.ComponentContext
import com.gifter.app.component.BaseChildComponent
import com.gifter.app.component.coroutineScope
import com.gifter.app.data.Repository
import com.gifter.app.data.model.response.Wish
import com.gifter.app.data.remote.RequestResult
import com.gifter.app.di.PlatformModule.diInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WishComponent(
	componentContext: ComponentContext,
	override val navigation: WishNavigation,
	override val onError: (RequestResult.Error) -> Unit,
	override val onLoading: (Boolean) -> Unit
) : BaseChildComponent, ComponentContext by componentContext {
	
	private val scope = coroutineScope()
	private val repository: Repository = diInstance()
	
	private val _wishesList = MutableStateFlow(listOf<Wish>())
	val wishesList: StateFlow<List<Wish>> get() = _wishesList
	
	fun createWish(name: String, description: String, urls: List<String>) {
		scope.launch {
			showLoading()
			val result = repository.createWish(name, description, urls)
			hideLoading()
			when (result) {
				is RequestResult.Success -> {
					getAllWishes()
				}
				is RequestResult.Error -> {
					displayError(result)
				}
			}
		}
	}
	
	fun getAllWishes() {
		scope.launch {
			showLoading()
			val result = repository.getAllWishes()
			hideLoading()
			when (result) {
				is RequestResult.Success -> {
					_wishesList.value = result.data
				}
				is RequestResult.Error -> {
					displayError(result)
				}
			}
		}
	}
}