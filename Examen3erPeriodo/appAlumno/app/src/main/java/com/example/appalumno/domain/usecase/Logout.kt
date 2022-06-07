package com.example.appalumno.domain.usecase

import com.example.appalumno.core.exception.Failure
import com.example.appalumno.core.functional.Either
import com.example.appalumno.core.interactor.UseCase
import com.example.appalumno.domain.repository.AlumnoRepository
import javax.inject.Inject

class Logout @Inject constructor(private val alumnoRepository: AlumnoRepository) :
    UseCase<Boolean, UseCase.None>()
{
    override suspend fun run(params: None): Either<Failure, Boolean> {
        return alumnoRepository.doLogout()
    }
}