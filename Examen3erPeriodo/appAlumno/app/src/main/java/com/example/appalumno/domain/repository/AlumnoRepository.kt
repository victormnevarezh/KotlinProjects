package com.example.appalumno.domain.repository

import com.example.appalumno.core.exception.Failure
import com.example.appalumno.core.functional.Either
import com.example.appalumno.data.dto.AlumnoResponse
import com.example.appalumno.domain.model.Alumno
import com.example.appalumno.domain.model.AlumnoUpdt

interface AlumnoRepository {
    fun getAlumnoByMatricula(matricula:String):Either<Failure, AlumnoResponse>
    fun updateAlumno(matricula: String, alumno: AlumnoUpdt):Either<Failure, AlumnoResponse>
    fun doLogout(): Either<Failure, Boolean>
    fun saveAlumno(alumno: Alumno):Either<Failure, Boolean>
}