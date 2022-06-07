package com.example.appmaestro.data.source

import com.example.appmaestro.core.exception.Failure
import com.example.appmaestro.core.functional.Either
import com.example.appmaestro.core.plataform.NetworkHandler
import com.example.appmaestro.data.api.AlumnoApi
import com.example.appmaestro.data.dto.AlumnoResponse
import com.example.appmaestro.domain.model.AlumnoUpdt
import com.example.appmaestro.domain.repository.AlumnoRepository
import com.example.appmaestro.framework.api.ApiRequest
import javax.inject.Inject

class AlumnoRepositoryImpl @Inject constructor(
    private val AlumnoApi : AlumnoApi,
    private val NetworkHandler: NetworkHandler
)
    : AlumnoRepository, ApiRequest {

    override fun getAlumnoByMatricula(matricula: String): Either<Failure, AlumnoResponse> {
        var result = makeRequest(
            NetworkHandler, AlumnoApi.getAlumnoByMatricula(matricula), {it}, AlumnoResponse(
                emptyList()
            )
        )
        return result
    }

    override fun updateAlumno(matricula: String, alumno : AlumnoUpdt): Either<Failure, AlumnoResponse> {
        var result = makeRequest(
            NetworkHandler, AlumnoApi.updateAlumno(matricula, alumno), {it}, AlumnoResponse(
                emptyList()
            )
        )
        return result
    }

}