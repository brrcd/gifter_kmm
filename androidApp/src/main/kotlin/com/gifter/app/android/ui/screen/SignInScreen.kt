package com.gifter.app.android.ui.screen

import android.content.IntentSender
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.gifter.app.android.BuildConfig
import com.gifter.app.android.findActivity
import com.gifter.app.component.signin.SignInComponent
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient

@Composable
fun SignInScreen(
	component: SignInComponent,
	modifier: Modifier = Modifier
) {
	
	var oneTapClient: SignInClient
	var signInRequest: BeginSignInRequest
	
	val activity = LocalContext.current.findActivity()
	
	val googleSignInResult = rememberLauncherForActivityResult(
		ActivityResultContracts.StartIntentSenderForResult()
	) { result ->
		// todo handle result not ok, cancel sign in
		oneTapClient = Identity.getSignInClient(activity)
		val credential = oneTapClient.getSignInCredentialFromIntent(result.data)
		val idToken = credential.googleIdToken
		if (idToken != null) {
			component.verifyToken(idToken)
		}
	}
	
	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(16.dp),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally,
	) {
		oneTapClient = Identity.getSignInClient(activity)
		fun beginSignIn(signInRequest: BeginSignInRequest) {
			oneTapClient.beginSignIn(signInRequest)
				.addOnSuccessListener(activity) { result ->
					try {
						googleSignInResult.launch(
							IntentSenderRequest.Builder(result.pendingIntent).build()
						)
					} catch (e: IntentSender.SendIntentException) {
						println()
					}
				}
				.addOnFailureListener(activity) { e ->
					println("one tap begin sign in exception: " + e.message)
				}
		}
		signInRequest = BeginSignInRequest.builder()
			.setPasswordRequestOptions(
				BeginSignInRequest.PasswordRequestOptions.builder()
					.setSupported(true)
					.build()
			)
			.setGoogleIdTokenRequestOptions(
				BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
					.setSupported(true)
					.setServerClientId(BuildConfig.OAUTH_CLIENT_ID)
					.setFilterByAuthorizedAccounts(true)
					.build()
			)
			.setAutoSelectEnabled(true)
			.build()
		
		Card(
			modifier = Modifier.size(128.dp),
			shape = CircleShape,
			elevation = 2.dp
		) {
			Image(
				imageVector = Icons.Default.Favorite, contentDescription = "App icon",
				modifier = Modifier
					.size(64.dp)
					.padding(16.dp)
			)
		}
		Spacer(modifier = Modifier.size(16.dp))
		Row(
			Modifier
				.fillMaxWidth()
				.align(Alignment.CenterHorizontally)
		) {
			Button(
				onClick = { beginSignIn(signInRequest) },
				modifier = Modifier.weight(1f)
			) {
				Text(text = "Google One Tap")
			}
			Spacer(modifier = Modifier.size(16.dp))
			Button(
				onClick = {
					Toast.makeText(activity, "Not implemented yet", Toast.LENGTH_SHORT).show()
				},
				modifier = Modifier.weight(1f)
			) {
				Text(text = "Apple SignIn")
			}
		}
	}
}