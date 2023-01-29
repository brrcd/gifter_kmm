package com.gifter.app.viewmodel

import com.gifter.app.data.remote.RequestResult
import com.gifter.app.usecase.VerifySignInUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.kodein.di.instance

class AuthViewModel: BaseViewModel() {
	
	private val verifySignInUseCase by di.instance<VerifySignInUseCase>()
	
	private val _jwtToken = MutableStateFlow("")
	val jwtToken: StateFlow<String> get() = _jwtToken
	
	fun verifyIdToken(idToken:String){
		coroutineScope.launch {
			_state.value = State.LOADING
			_state.value = when (val result = verifySignInUseCase.invoke(idToken)) {
				is RequestResult.Success -> {
					println(result.data.token)
					_jwtToken.value = result.data.token
					State.NORMAL
				}
				is RequestResult.Error -> {
					println("${result.error.errorCode} ${result.error.errorMessage}")
					_errorText.value = result.error.errorMessage
					State.ERROR
				}
			}
		}
	}
}
