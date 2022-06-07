package com.example.appalumno.domain.usecase

import com.example.appalumno.core.exception.Failure
import com.example.appalumno.core.functional.Either
import com.example.appalumno.core.interactor.UseCase
import com.example.appalumno.data.dto.AlumnoResponse
import com.example.appalumno.domain.repository.AlumnoRepository
import javax.inject.Inject

class getAlumnoByMatricula @Inject constructor(private val alumnoRepository: AlumnoRepository):
    UseCase<AlumnoResponse, String>(){
    override suspend fun run(params: String): Either<Failure, AlumnoResponse> {
        return alumnoRepository.getAlumnoByMatricula(params)
    }

}