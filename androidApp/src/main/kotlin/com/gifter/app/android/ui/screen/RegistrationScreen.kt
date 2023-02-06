package com.gifter.app.android.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gifter.app.component.registration.RegistrationComponent

@Composable
fun RegistrationScreen(
	component: RegistrationComponent,
	modifier: Modifier = Modifier
) {
	
	var name by remember { mutableStateOf("") }
	var id by remember { mutableStateOf("") }
	
	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(16.dp),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Text(
			text = "Enter your name"
		)
		TextField(
			value = name, onValueChange = { name = it })
		Text(
			text = "Enter your identifier"
		)
		TextField(
			value = id, onValueChange = { id = it }
		)
		Button(
			onClick = { component.registerUser(id, name) }) {
			Text(text = "Register")
		}
	}
} 