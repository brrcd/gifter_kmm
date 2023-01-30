package com.gifter.app.android.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.gifter.app.android.ui.screen.RegistrationScreen
import com.gifter.app.android.ui.screen.SignInScreen

@Composable
fun AppNavHost(
	modifier: Modifier,
	navController: NavHostController = rememberNavController(),
	startDestination: String = SIGN_IN_SCREEN
) {
	NavHost(modifier = modifier,
	navController = navController,
	startDestination = startDestination){
		composable(SIGN_IN_SCREEN) {
			SignInScreen(onTokenSuccess = { navController.navigate(REGISTRATION_SCREEN) {
				launchSingleTop = true
				
			} })
		}
		composable(REGISTRATION_SCREEN) {
			RegistrationScreen()
		}
	}
}

private const val SIGN_IN_SCREEN = "sign-in"
private const val REGISTRATION_SCREEN = "registration"