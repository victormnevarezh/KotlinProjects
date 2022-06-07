package com.example.appmaestro.domain.usecase

import com.example.appmaestro.core.exception.Failure
import com.example.appmaestro.core.functional.Either
import com.example.appmaestro.core.interactor.UseCase
import com.example.appmaestro.data.dto.AlumnoResponse
import com.example.appmaestro.domain.repository.AlumnoRepository
import javax.inject.Inject

class getAlumnoByMatricula @Inject constructor(private val alumnoRepository: AlumnoRepository):
    UseCase<AlumnoResponse, String>() {
    override suspend fun run(params: String): Either<Failure, AlumnoResponse> {
        return alumnoRepository.getAlumnoByMatricula(params)
    }
}