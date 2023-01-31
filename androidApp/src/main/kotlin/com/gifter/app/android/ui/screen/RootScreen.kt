package com.gifter.app.android.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.gifter.app.component.root.Root

@OptIn(ExperimentalDecomposeApi::class)
@Composable
fun RootScreen(
	component: Root,
	modifier: Modifier
) {
	
	Children(stack = component.childStack) {
		when (val child = it.instance) {
			is Root.Child.Main -> MainScreen(component = child.component)
			is Root.Child.SignIn -> SignInScreen(component = child.component)
		}
	}
}