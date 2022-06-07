package com.example.appmaestro.domain.usecase

import com.example.appmaestro.core.exception.Failure
import com.example.appmaestro.core.functional.Either
import com.example.appmaestro.core.interactor.UseCase
import com.example.appmaestro.core.platform.AuthManager
import com.example.appmaestro.domain.model.Materia
import javax.inject.Inject

class getMateriaSP @Inject constructor(private val authManager: AuthManager) : UseCase<Materia, UseCase.None>() {

    override suspend fun run(params: None): Either<Failure, Materia> {
        var result = authManager.materia
        return result?.let{
            Either.Right(it)
        }?:Either.Left(Failure.Unauthorized)
    }

}