package com.gifter.app.android.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.gifter.app.android.ui.component.CircularLoading
import com.gifter.app.android.ui.component.InfoPopup
import com.gifter.component.root.Root
import com.gifter.component.root.Root.Child

@OptIn(ExperimentalDecomposeApi::class)
@Composable
fun RootScreen(
	component: Root,
	modifier: Modifier = Modifier
) {
	val infoOverlay by component.popupOverlay.subscribeAsState()
	val loadingOverlay by component.loadingOverlay.subscribeAsState()
	
	Column(modifier = modifier) {
		Children(
			stack = component.childStack,
			modifier = Modifier.weight(1F)
		) {
			when (val child = it.instance) {
				is Child.Main -> MainScreen(
					component = child.component,
					modifier = Modifier.fillMaxSize()
				)
				is Child.SignIn -> SignInScreen(
					component = child.component,
					modifier = Modifier.fillMaxSize()
				)
				is Child.Registration -> RegistrationScreen(
					component = child.component,
					modifier = Modifier.fillMaxSize()
				)
			}
		}
	}
	infoOverlay.overlay?.instance?.let {
		InfoPopup(component = it)
	}
	loadingOverlay.overlay?.instance?.let {
		CircularLoading()
	}
}