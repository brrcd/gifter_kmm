package com.gifter.app.android.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.gifter.app.component.root.Root
import com.gifter.app.component.root.Root.Child

@OptIn(ExperimentalDecomposeApi::class)
@Composable
fun RootScreen(
	component: Root,
	modifier: Modifier = Modifier
) {
	
	Children(
		stack = component.childStack,
		modifier = modifier
	) {
		when (val child = it.instance) {
			is Child.Main -> MainScreen(component = child.component)
			is Child.SignIn -> SignInScreen(component = child.component)
			is Child.Registration -> RegistrationScreen(component = child.component)
		}
	}
}