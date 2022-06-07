package com.example.appmaestro.domain.usecase

import com.example.appmaestro.core.exception.Failure
import com.example.appmaestro.core.functional.Either
import com.example.appmaestro.core.interactor.UseCase
import com.example.appmaestro.data.dto.MaestroResponse
import com.example.appmaestro.domain.model.Maestro
import com.example.appmaestro.domain.model.MaestroUpdt
import com.example.appmaestro.domain.repository.MaestroRepository
import javax.inject.Inject

class updateMaestro @Inject constructor(private val maestroRepository: MaestroRepository)
    : UseCase<MaestroResponse, Maestro>()
{
    override suspend fun run(params: Maestro): Either<Failure, MaestroResponse> {
        val send = MaestroUpdt(params.foto,params.nombre,params.aPaterno,params.aMaterno,
        params.correo,params.password,params.materias)
        return maestroRepository.updateMaestro(params.matricula,send)
    }

}