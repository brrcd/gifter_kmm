package com.gifter.app.component.home

import com.arkivanov.decompose.ComponentContext
import com.gifter.app.component.coroutineScope
import com.gifter.app.data.Repository
import com.gifter.app.di.PlatformModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class HomeComponent(
	val componentContext: ComponentContext,
	val navigation: HomeNavigation
) : ComponentContext by componentContext {
	
	private val scope = coroutineScope()
	
	private val repository: Repository = PlatformModule.instance()
	
	// TODO REMOVE
	fun clearJWT(){
		repository.removeJWT()
	}
}