package com.gifter.app.android.ui.screen

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.gifter.app.android.BuildConfig
import com.gifter.app.android.findActivity
import com.gifter.app.component.signin.SignInComponent
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException

@Composable
fun SignInScreen(
	component: SignInComponent,
	modifier: Modifier = Modifier
) {
	
	val activity = LocalContext.current.findActivity()
	var text by remember { mutableStateOf("") }
	
	val googleSignInResult = rememberLauncherForActivityResult(
		ActivityResultContracts.StartActivityForResult()
	) { result ->
		if (result.resultCode == -1) {
			val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
			try {
				val account = task.getResult(ApiException::class.java)
				val idToken = account.idToken ?: ""
				text = idToken
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
	Box() {
		Button(
			onClick = { googleSignInResult.launch(googleSignInClient().signInIntent) },
			modifier = Modifier.align(Alignment.Center)
		) {
			Text(text = "Sign in with google")
		}
		Text(text = text)
	}
}