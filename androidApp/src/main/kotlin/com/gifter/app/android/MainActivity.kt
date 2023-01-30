package com.gifter.app.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.gifter.app.android.theme.Theme
import com.gifter.app.android.ui.AppNavHost
import com.gifter.app.android.ui.screen.SignInScreen
import com.gifter.app.viewmodel.AuthViewModel

class MainActivity : ComponentActivity() {
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			Theme {
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colors.background
				) {
					AppNavHost(modifier = Modifier.fillMaxSize())
				}
			}
		}
	}
}

@Composable
fun GreetingView(text: String) {
	Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
	Theme {
		GreetingView("Hello, Android!")
	}
}
