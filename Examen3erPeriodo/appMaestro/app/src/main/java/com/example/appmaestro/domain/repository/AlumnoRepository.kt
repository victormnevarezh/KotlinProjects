package com.example.appmaestro.domain.repository

import com.example.appmaestro.core.exception.Failure
import com.example.appmaestro.core.functional.Either
import com.example.appmaestro.data.dto.AlumnoResponse
import com.example.appmaestro.domain.model.AlumnoUpdt

interface AlumnoRepository {
    fun getAlumnoByMatricula(matricula:String): Either<Failure, AlumnoResponse>
    fun updateAlumno(matricula: String, alumno : AlumnoUpdt):Either<Failure, AlumnoResponse>
}