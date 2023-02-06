package com.gifter.app.component.infopopup

import com.arkivanov.decompose.ComponentContext

class PopupComponent (
	val componentContext: ComponentContext,
	val errorMessage: String
): ComponentContext by componentContext{

}