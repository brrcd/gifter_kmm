package com.gifter.app.viewmodel

import com.gifter.app.data.model.response.User
import com.gifter.app.data.remote.RequestResult
import com.gifter.app.usecase.RegisterUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.kodein.di.instance

class RegistrationViewModel: BaseViewModel() {
	
	private val registerUserUseCase by di.instance<RegisterUserUseCase>()
	
	private val _user = MutableStateFlow<User?>(null)
	val user: StateFlow<User?> get() = _user
	
	fun registerUser(name: String){
		coroutineScope.launch {
			_state.value = State.LOADING
			_state.value = when (val result = registerUserUseCase.invoke(name)){
				is RequestResult.Success -> {
					_user.value = result.data
					State.NORMAL
				}
				is RequestResult.Error -> {
					_errorText.value = "${result.error.errorCode} ${result.error.errorMessage}"
					State.ERROR
				}
			}
		}
	}
}