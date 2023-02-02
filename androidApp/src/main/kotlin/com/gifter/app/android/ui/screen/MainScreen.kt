package com.gifter.app.android.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.gifter.app.component.main.MainComponent

@Composable
fun MainScreen(
	component: MainComponent,
	modifier: Modifier = Modifier
) {
	
	Column() {
		Text(text = "Main screen")
		Button(onClick = { component.clearJWT() }) {
			Text(text = "Clear token")
		}
	}
}