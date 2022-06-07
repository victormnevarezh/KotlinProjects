package com.example.appmaestro.domain.usecase

import com.example.appmaestro.core.exception.Failure
import com.example.appmaestro.core.functional.Either
import com.example.appmaestro.core.interactor.UseCase
import com.example.appmaestro.core.platform.AuthManager
import com.example.appmaestro.domain.model.Maestro
import javax.inject.Inject

class getMaestroSP @Inject constructor(private val authManager: AuthManager)
    : UseCase<Maestro, UseCase.None>() {
    override suspend fun run(params: None): Either<Failure, Maestro> {
        var result = authManager.maestro
        return result?.let{
            Either.Right(it)
        }?:Either.Left(Failure.Unauthorized)
    }
}