package com.example.appmaestro.data.source

import com.example.appmaestro.core.exception.Failure
import com.example.appmaestro.core.functional.Either
import com.example.appmaestro.core.plataform.NetworkHandler
import com.example.appmaestro.core.platform.AuthManager
import com.example.appmaestro.data.api.MaestroApi
import com.example.appmaestro.data.dto.MaestroResponse
import com.example.appmaestro.domain.model.Maestro
import com.example.appmaestro.domain.model.MaestroUpdt
import com.example.appmaestro.domain.repository.MaestroRepository
import com.example.appmaestro.framework.api.ApiRequest
import javax.inject.Inject

class MaestroRepositoryImpl @Inject constructor(
    private val MaestroApi : MaestroApi,
    private val authManager: AuthManager,
    private val NetworkHandler : NetworkHandler)
    : MaestroRepository, ApiRequest {

    override fun getMaestroByMatricula(matricula: String): Either<Failure, MaestroResponse> {
        var result = makeRequest(
            NetworkHandler, MaestroApi.getMaestroByMatricula(matricula), {it}, MaestroResponse(
                emptyList()
            )
        )
        return result
    }

    override fun updateMaestro(matricula: String, maestro: MaestroUpdt): Either<Failure, MaestroResponse> {
        var result = makeRequest(
            NetworkHandler,
            MaestroApi.updateMaestro(matricula, maestro),
            {it},
            MaestroResponse(emptyList())
        )
        return result
    }

    override fun saveMaestro(maestro: Maestro): Either<Failure, Boolean> {
        authManager.maestro = maestro
        return Either.Right(true)
    }

    override fun doLogout(): Either<Failure, Boolean> {
        authManager.maestro = null
        return Either.Right(true)
    }

}
