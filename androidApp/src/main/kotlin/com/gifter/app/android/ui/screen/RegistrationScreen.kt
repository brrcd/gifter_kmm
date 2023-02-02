package com.gifter.app.android.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.gifter.app.component.registration.RegistrationComponent

@Composable
fun RegistrationScreen(
	component: RegistrationComponent,
	modifier: Modifier = Modifier
) {
	Box(modifier = Modifier.fillMaxSize()) {
		Text(text = "Registration screen", modifier = Modifier.align(Alignment.Center))
	}
} 