package com.example.appalumno.data.source

import com.example.appalumno.core.exception.Failure
import com.example.appalumno.core.functional.Either
import com.example.appalumno.core.plataform.NetworkHandler
import com.example.appalumno.data.api.MateriaApi
import com.example.appalumno.data.dto.AlumnoResponse
import com.example.appalumno.data.dto.MateriaResponse
import com.example.appalumno.domain.repository.MateriaRepository
import com.example.appalumno.framework.api.ApiRequest
import javax.inject.Inject

class MateriaRepositoryImpl @Inject constructor(
    private val MateriaApi : MateriaApi,
    private val NetworkHandler : NetworkHandler
): MateriaRepository, ApiRequest {
    override fun getMateria(nombre: String): Either<Failure, MateriaResponse> {
        var result = makeRequest(
            NetworkHandler, MateriaApi.getMateria(nombre), {it}, MateriaResponse(
                emptyList()
            )
        )
        return result
    }

}