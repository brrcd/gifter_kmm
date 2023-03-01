package com.gifter.app.android.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gifter.component.wish.WishComponent
import com.gifter.data.model.response.Wish

@Composable
fun WishScreen(
	component: WishComponent,
	modifier: Modifier = Modifier
) {
	val wishesList by component.wishesList.collectAsState()
	
	Column() {
		LazyColumn(
			modifier = Modifier.fillMaxWidth(),
			contentPadding = PaddingValues(vertical = 16.dp, horizontal = 8.dp)
		)
		{
			items(wishesList) { item ->
				WishItem(item = item)
			}
		}
		Button(onClick = { component.createWish("name", "description", listOf("","1","2")) }) {
			Text(text = "add wish")
		}
	}
}

@Composable
fun WishItem(
	item: Wish
) {
	Row() {
		Text(text = item.name)
	}
}