package com.example.appalumno.domain.usecase

import com.example.appalumno.core.exception.Failure
import com.example.appalumno.core.functional.Either
import com.example.appalumno.core.interactor.UseCase
import com.example.appalumno.data.dto.AlumnoResponse
import com.example.appalumno.domain.model.Alumno
import com.example.appalumno.domain.model.AlumnoUpdt
import com.example.appalumno.domain.repository.AlumnoRepository
import javax.inject.Inject

class updateAlumno @Inject constructor(private val AlumnoRepository: AlumnoRepository) :
    UseCase<AlumnoResponse,Alumno> (){
    override suspend fun run(params: Alumno): Either<Failure, AlumnoResponse> {
        val send = AlumnoUpdt(params.foto,params.nombre,params.aPaterno,params.aMaterno,
            params.correo, params.password, params.materias,
            params.calificaciones1, params.calificaciones2, params.calificaciones3)
        return AlumnoRepository.updateAlumno(params.matricula, send)
    }

}