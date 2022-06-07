package com.example.appalumno.domain.usecase

import com.example.appalumno.core.exception.Failure
import com.example.appalumno.core.functional.Either
import com.example.appalumno.core.interactor.UseCase
import com.example.appalumno.core.platform.AuthManager
import com.example.appalumno.domain.model.Alumno
import com.example.appalumno.domain.repository.AlumnoRepository
import javax.inject.Inject

class saveAlumnoSP @Inject constructor(private val alumnoRepository: AlumnoRepository):
UseCase<Boolean,Alumno>(){
    override suspend fun run(params: Alumno): Either<Failure, Boolean> {
        return alumnoRepository.saveAlumno(params)
    }

}