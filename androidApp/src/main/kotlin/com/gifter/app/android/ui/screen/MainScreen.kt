package com.gifter.app.android.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.arkivanov.decompose.router.stack.childStack
import com.gifter.app.component.main.Main
import com.gifter.app.component.main.MainComponent
import com.gifter.app.component.root.Root

@OptIn(ExperimentalDecomposeApi::class)
@Composable
fun MainScreen(
	component: MainComponent,
	modifier: Modifier = Modifier
) {
	val childStack by component.childStack.subscribeAsState()
	val activeComponent = childStack.active.instance
	
	Column(modifier = modifier) {
		Children(
			stack = component.childStack,
			modifier = Modifier.weight(1F)
		) {
			when (val child = it.instance) {
				is Main.Child.Home -> HomeScreen(
					component = child.component,
					modifier = Modifier.fillMaxSize()
				)
				is Main.Child.Profile -> ProfileScreen(
					component = child.component,
					modifier = Modifier.fillMaxSize()
				)
				is Main.Child.Wish -> WishScreen(
					component = child.component,
					modifier = Modifier.fillMaxSize()
				)
			}
		}
		BottomNavigation(
			modifier = Modifier
				.fillMaxWidth()
		) {
			BottomNavigationItem(selected = activeComponent is Main.Child.Home, onClick = {
				component.onHomeTabSelected()
			},
				icon = {
					Icon(
						imageVector = Icons.Default.Home,
						contentDescription = "Home screen"
					)
				})
			BottomNavigationItem(selected = activeComponent is Main.Child.Wish, onClick = {
				component.onWishTabSelected()
			},
				icon = {
					Icon(
						imageVector = Icons.Default.ShoppingCart,
						contentDescription = "Wish screen"
					)
				})
			BottomNavigationItem(selected = activeComponent is Main.Child.Profile, onClick = {
				component.onProfileTabSelected()
			},
				icon = {
					Icon(
						imageVector = Icons.Default.Settings,
						contentDescription = "Profile screen"
					)
				})
		}
	}
}