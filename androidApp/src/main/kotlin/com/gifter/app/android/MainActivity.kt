package com.gifter.app.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.defaultComponentContext
import com.gifter.app.android.theme.Theme
import com.gifter.app.android.ui.screen.RootScreen
import com.gifter.app.component.root.Root
import com.gifter.app.component.root.RootComponent

class MainActivity : ComponentActivity() {
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		
		val root = root(defaultComponentContext())
		
		setContent {
			Theme {
				Surface(
					color = MaterialTheme.colors.background
				) {
					RootScreen(
						component = root,
						modifier = Modifier.fillMaxSize()
					)
				}
			}
		}
	}
	
	private fun root(componentContext: ComponentContext): Root =
		RootComponent(
			componentContext = componentContext
		)
}
