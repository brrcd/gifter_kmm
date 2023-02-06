package com.gifter.app.android.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gifter.app.component.infopopup.PopupComponent

@Composable
fun InfoPopup(
	component: PopupComponent,
	modifier: Modifier = Modifier
) {
	Column(modifier = Modifier.fillMaxSize()) {
		
		Spacer(modifier = Modifier.weight(1F))
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.weight(0.1F),
			horizontalArrangement = Arrangement.Center
		) {
			Text(text = component.errorMessage)
		}
	}
}