package com.example.appmaestro.domain.usecase

import com.example.appmaestro.core.exception.Failure
import com.example.appmaestro.core.functional.Either
import com.example.appmaestro.core.interactor.UseCase
import com.example.appmaestro.data.dto.AlumnoResponse
import com.example.appmaestro.domain.model.Alumno
import com.example.appmaestro.domain.model.AlumnoUpdt
import com.example.appmaestro.domain.repository.AlumnoRepository
import javax.inject.Inject

class updateAlumno @Inject constructor(private val alumnoRepository: AlumnoRepository): UseCase<AlumnoResponse, Alumno>() {

    override suspend fun run(params: Alumno): Either<Failure, AlumnoResponse> {
        val send = AlumnoUpdt(params.foto,params.nombre,params.aPaterno,params.aMaterno,
            params.correo, params.password, params.materias,
            params.calificaciones1, params.calificaciones2, params.calificaciones3)
        return alumnoRepository.updateAlumno(params.matricula, send)
    }

}