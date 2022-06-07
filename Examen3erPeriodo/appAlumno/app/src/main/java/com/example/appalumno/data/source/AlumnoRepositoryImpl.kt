package com.example.appalumno.data.source

import com.example.appalumno.core.exception.Failure
import com.example.appalumno.core.functional.Either
import com.example.appalumno.core.plataform.NetworkHandler
import com.example.appalumno.core.platform.AuthManager
import com.example.appalumno.data.api.AlumnoApi
import com.example.appalumno.data.dto.AlumnoResponse
import com.example.appalumno.domain.model.Alumno
import com.example.appalumno.domain.model.AlumnoUpdt
import com.example.appalumno.domain.repository.AlumnoRepository
import com.example.appalumno.framework.api.ApiRequest
import javax.inject.Inject

class AlumnoRepositoryImpl @Inject constructor(
    private val AlumnoApi : AlumnoApi,
    private val authManager: AuthManager,
    private val NetworkHandler: NetworkHandler )
    : AlumnoRepository, ApiRequest {

    override fun getAlumnoByMatricula(matricula: String): Either<Failure, AlumnoResponse> {
        var result = makeRequest(
            NetworkHandler, AlumnoApi.getAlumnoByMatricula(matricula), {it}, AlumnoResponse(
                emptyList()
            )
        )
       return result
    }

    override fun updateAlumno(matricula: String, alumno: AlumnoUpdt): Either<Failure, AlumnoResponse> {
        var result = makeRequest(
            NetworkHandler, AlumnoApi.updateAlumno(matricula,alumno), {it}, AlumnoResponse(
            emptyList()
            )
        )
        return result
    }

    override fun doLogout(): Either<Failure, Boolean> {
        authManager.alumno = null
        return Either.Right(true)
    }

    override fun saveAlumno(alumno: Alumno): Either<Failure, Boolean> {
        authManager.alumno = alumno
        return Either.Right(true)
    }

}