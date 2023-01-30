package com.gifter.app.android.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import com.gifter.app.viewmodel.RegistrationViewModel
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import com.gifter.app.viewmodel.BaseViewModel

@Composable
fun RegistrationScreen(){
	val viewModel = remember { RegistrationViewModel() }
	val state by viewModel.state.collectAsState()

	when (state) {
		BaseViewModel.State.LOADING -> {
			
		}
		BaseViewModel.State.NORMAL -> {
			Column() {
				Text(text = "OK")
				Button(onClick = { viewModel.registerUser("my name") }) {
				
				}
			}
		}
		BaseViewModel.State.ERROR -> {
			
		}
	}
} 