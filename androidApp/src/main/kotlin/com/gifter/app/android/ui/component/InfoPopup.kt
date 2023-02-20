package com.gifter.app.android.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gifter.app.component.infopopup.PopupComponent

@Composable
fun InfoPopup(
	component: PopupComponent,
	modifier: Modifier = Modifier
) {
	Column(modifier = Modifier.fillMaxHeight()) {
		
		Spacer(modifier = Modifier.weight(1F))
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.padding(bottom = 16.dp, start = 16.dp, end = 16.dp),
			horizontalArrangement = Arrangement.Center
		) {
			Text(text = component.errorMessage)
		}
	}
}