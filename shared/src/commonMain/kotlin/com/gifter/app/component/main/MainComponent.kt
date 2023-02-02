package com.gifter.app.component.main

import com.arkivanov.decompose.ComponentContext
import com.gifter.app.component.BaseComponent
import com.gifter.app.component.coroutineScope
import com.gifter.app.data.Repository
import com.gifter.app.di.PlatformModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class MainComponent(componentContext: ComponentContext) : BaseComponent(), ComponentContext by componentContext {
	
	override val scope: CoroutineScope = coroutineScope(Dispatchers.Default + SupervisorJob())
	
	private val repository: Repository = PlatformModule.instance()
	
	init {
		apiCall(
			{ repository.getUser() },
			{ println(it.data.name) },
			{ println(it.error.errorMessage) }
		)
	}
	
	// TODO REMOVE
	fun clearJWT(){
		repository.removeJWT()
	}
}