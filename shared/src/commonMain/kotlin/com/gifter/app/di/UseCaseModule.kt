package com.gifter.app.di

import com.gifter.app.usecase.RegisterUserUseCase
import com.gifter.app.util.USE_CASE_DI_MODULE
import com.gifter.app.usecase.VerifySignInUseCase
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider

class UseCaseModule : DIModule {
	override val module = DI.Module(USE_CASE_DI_MODULE) {
		bind<VerifySignInUseCase>() with provider { VerifySignInUseCase(instance()) }
		bind<RegisterUserUseCase>() with provider { RegisterUserUseCase(instance()) }
	}
}