package com.gifter.app.android.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.gifter.component.home.HomeComponent

@Composable
fun HomeScreen(
	component: HomeComponent,
	modifier: Modifier = Modifier
) {
	val user = component.user.subscribeAsState()
	
	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(16.dp),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Text(text = user.value.name)
		Text(text = "Home screen")
		Button(onClick = { component.clearJWT() }) {
			Text(text = "Clear token")
		}
		Button(onClick = { component.navigation.navigateRegistration() }) {
			Text(text = "Test go to registration")
		}
	}
}