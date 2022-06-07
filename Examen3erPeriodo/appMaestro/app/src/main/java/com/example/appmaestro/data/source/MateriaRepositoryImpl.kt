package com.example.appmaestro.data.source

import com.example.appmaestro.core.exception.Failure
import com.example.appmaestro.core.functional.Either
import com.example.appmaestro.core.plataform.NetworkHandler
import com.example.appmaestro.core.platform.AuthManager
import com.example.appmaestro.data.api.MateriaApi
import com.example.appmaestro.data.dto.MateriaResponse
import com.example.appmaestro.domain.model.Materia
import com.example.appmaestro.domain.repository.MateriaRepository
import com.example.appmaestro.framework.api.ApiRequest
import javax.inject.Inject

class MateriaRepositoryImpl @Inject constructor(private val materiaApi : MateriaApi, private val authManager: AuthManager,private val NetworkHandler : NetworkHandler) : MateriaRepository, ApiRequest {

    override fun getMateria(nombre: String): Either<Failure, MateriaResponse> {
        var result = makeRequest(
            NetworkHandler, materiaApi.getMateria(nombre), {it}, MateriaResponse(
                emptyList()
            )
        )
        return result
    }

    override fun saveMateria(materia: Materia): Either<Failure, Boolean> {
        authManager.materia = materia
        return Either.Right(true)
    }


}