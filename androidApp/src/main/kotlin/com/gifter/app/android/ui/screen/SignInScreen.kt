package com.gifter.app.android.ui.screen

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.gifter.app.android.BuildConfig
import com.gifter.app.android.MainActivity
import com.gifter.app.android.findActivity
import com.gifter.app.android.ui.components.CircularLoading
import com.gifter.app.viewmodel.AuthViewModel
import com.gifter.app.viewmodel.BaseViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException

@Composable
fun SignInScreen(
	onTokenSuccess: () -> Unit
) {
	
	val viewModel = remember { AuthViewModel() }
	val state by viewModel.state.collectAsState()
	val jwtToken by viewModel.jwtToken.collectAsState()
	val activity = LocalContext.current.findActivity()
	
	val googleSignInResult = rememberLauncherForActivityResult(
		ActivityResultContracts.StartActivityForResult()
	) { result ->
		if (result.resultCode == -1) {
			val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
			try {
				val account = task.getResult(ApiException::class.java)
				val idToken = account.idToken ?: ""
				viewModel.verifyIdToken(idToken)
			} catch (e: ApiException) {
				println("Exception at get google sing in result :\n" + e.message)
			}
		} else {
			println("result is not ok : " + result.resultCode)
		}
	}
	
	val googleSignInClient: () -> GoogleSignInClient = {
		val serverClientId = BuildConfig.SERVER_CLIENT_ID
		val options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
			.requestIdToken(serverClientId)
			.requestEmail()
			.build()
		GoogleSignIn.getClient(activity, options)
	}
	
	when (state) {
		BaseViewModel.State.LOADING -> {
			CircularLoading()
		}
		BaseViewModel.State.NORMAL -> {
			if (jwtToken.isNotEmpty()) onTokenSuccess.invoke()
			Column(Modifier.fillMaxSize()) {
				var expanded by remember { mutableStateOf(false) }
				Button(onClick = { googleSignInResult.launch(googleSignInClient().signInIntent) }) {
					Text(text = "Sign in with google")
				}
				Text(
					text = jwtToken,
					modifier = Modifier
						.align(Alignment.CenterHorizontally)
						.clickable { expanded = !expanded },
					maxLines = if (expanded) 10 else 1,
				)
			}
		}
		BaseViewModel.State.ERROR -> {
			Column(Modifier.fillMaxSize()) {
				Text(text = viewModel.errorText.value)
			}
		}
	}
}