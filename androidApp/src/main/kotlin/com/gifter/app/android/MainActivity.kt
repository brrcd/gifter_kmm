package com.gifter.app.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.gifter.app.android.theme.Theme
import com.gifter.app.android.ui.SignInScreen
import com.gifter.app.data.Repository
import com.gifter.app.di.RepositoryModule
import com.gifter.app.viewmodel.AuthViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import kotlinx.coroutines.launch
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.instance

class MainActivity : ComponentActivity() {
	
	private val authViewModel = AuthViewModel()
	var idToken = "empty"
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			Theme {
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colors.background
				) {
					SignInScreen(activity = this)
//					val googleSignInResult = rememberLauncherForActivityResult(
//						ActivityResultContracts.StartActivityForResult()
//					) { result ->
//						if (result.resultCode == -1) {
//							val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
//							try {
//								val account = task.getResult(ApiException::class.java)
//								idToken = account.idToken ?: "error"
//								println(idToken)
//							} catch (e: ApiException){
//								println("Exception at get google sing in result :\n" + e.message)
//							}
//						} else {
//							println("jiopa :" + result.resultCode)
//						}
//					}
//					val scope = rememberCoroutineScope()
//					var text by remember { mutableStateOf("Loading") }
//					LaunchedEffect(true) {
//						scope.launch {
//							text = try {
//								"text"
//							} catch (e: Exception) {
//								e.localizedMessage ?: "error"
//							}
//						}
//					}
//					GreetingView(text)
//					Column() {
//						Button(onClick = { googleSignInResult.launch(getGoogleSignInClient().signInIntent) },
//							Modifier
//								.fillMaxWidth()
//								.wrapContentHeight()) {
//							Text("Get token")
//						}
//						SendIdTokenButton(authViewModel = authViewModel)
//					}
				}
			}
		}
	}
//
//	private fun getGoogleSignInClient(): GoogleSignInClient {
//		val serverClientId = this.getString(R.string.server_client_id)
//		val options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//			.requestIdToken(serverClientId)
//			.requestEmail()
//			.build()
//		return GoogleSignIn.getClient(this, options)
//	}
//
//	@Composable
//	private fun SendIdTokenButton(authViewModel: AuthViewModel) {
//		val coroutineScope = rememberCoroutineScope()
//		val sendIdTokenOnClick: () -> Unit = {
//			coroutineScope.launch {
//				authViewModel.verifyIdToken(idToken)
//			}
//		}
//		Button(onClick = sendIdTokenOnClick,
//			Modifier
//				.fillMaxWidth()
//				.wrapContentHeight()) {
//			Text("Send id token")
//		}
//	}
//}
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
