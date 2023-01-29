package com.gifter.app.viewmodel

import com.gifter.app.di.RepositoryModule
import com.gifter.app.di.UseCaseModule
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.kodein.di.DI
import org.kodein.di.DIAware

abstract class BaseViewModel: CoroutineViewModel(), DIAware {
	
	override val di by DI.lazy {
		importAll(
			RepositoryModule().module,
			UseCaseModule().module
		)
	}
	
	protected val _state = MutableStateFlow(State.NORMAL)
	val state: StateFlow<State> get() = _state
	
	protected val _errorText = MutableStateFlow("")
	val errorText: StateFlow<String> get() = _errorText
	
	enum class State {
		LOADING,
		NORMAL,
		ERROR
	}
}