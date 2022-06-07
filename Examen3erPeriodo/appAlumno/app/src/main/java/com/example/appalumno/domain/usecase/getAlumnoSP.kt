package com.example.appalumno.domain.usecase

import com.example.appalumno.core.exception.Failure
import com.example.appalumno.core.functional.Either
import com.example.appalumno.core.interactor.UseCase
import com.example.appalumno.core.platform.AuthManager
import com.example.appalumno.domain.model.Alumno
import javax.inject.Inject

class getAlumnoSP @Inject constructor(private val authManager: AuthManager) : UseCase<Alumno, UseCase.None>() {
    override suspend fun run(params: None): Either<Failure, Alumno> {
        var result = authManager.alumno

        return result?.let{
            Either.Right(it)
        }?:Either.Left(Failure.Unauthorized)
    }

}