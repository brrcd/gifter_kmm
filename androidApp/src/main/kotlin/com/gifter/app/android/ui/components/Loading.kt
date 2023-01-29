package com.gifter.app.android.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun CircularLoading() {
	Column(Modifier.fillMaxSize()) {
		CircularProgressIndicator(
			modifier = Modifier
				.align(Alignment.CenterHorizontally)
		)
	}
}