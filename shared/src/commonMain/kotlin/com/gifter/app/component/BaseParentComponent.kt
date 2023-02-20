package com.gifter.app.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.overlay.ChildOverlay
import com.arkivanov.decompose.router.overlay.OverlayNavigation
import com.arkivanov.decompose.router.overlay.activate
import com.arkivanov.decompose.router.overlay.childOverlay
import com.arkivanov.decompose.router.overlay.dismiss
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.gifter.app.component.infopopup.PopupComponent
import com.gifter.app.component.loading.LoadingComponent
import com.gifter.app.component.root.RootComponent
import com.gifter.app.data.remote.RequestResult
import com.gifter.app.runOnMain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

interface BaseParentComponent {
	
	fun onError(result: RequestResult.Error)
	fun onLoading(isLoading: Boolean)
	
	val popupOverlay: Value<ChildOverlay<*, PopupComponent>>
	val loadingOverlay: Value<ChildOverlay<*, LoadingComponent>>
}

abstract class BaseParentComponentImpl(componentContext: ComponentContext) : BaseParentComponent,
	ComponentContext by componentContext {
	
	private val popUpNavigation = OverlayNavigation<PopUpConfig>()
	private val loadingNavigation = OverlayNavigation<Loading>()
	
	private val scope = coroutineScope()
	
	private val popupChild = childOverlay(
		source = popUpNavigation,
		key = "popUp",
		handleBackButton = true
	) { config, componentContext ->
		PopupComponent(
			componentContext = componentContext,
			config.message
		)
	}
	
	override val popupOverlay: Value<ChildOverlay<*, PopupComponent>> = popupChild
	override val loadingOverlay: Value<ChildOverlay<*, LoadingComponent>> =
		childOverlay(
			source = loadingNavigation,
			key = "loading"
		) { _, _ -> LoadingComponent() }
	
	override fun onLoading(isLoading: Boolean) {
		scope.launch {
			runOnMain {
				if (isLoading) loadingNavigation.activate(Loading)
				else loadingNavigation.dismiss()
			}
		}
	}
	
	override fun onError(result: RequestResult.Error) {
		scope.launch {
			runOnMain {
				val config = PopUpConfig(result.error.errorMessage)
				popUpNavigation.activate(config)
			}
			withContext(Dispatchers.Default) {
				delay(5000)
			}
			runOnMain {
				popUpNavigation.dismiss()
			}
		}
	}
	
	@Parcelize
	data class PopUpConfig(
		val message: String
	) : Parcelable
	
	@Parcelize
	object Loading : Parcelable
}