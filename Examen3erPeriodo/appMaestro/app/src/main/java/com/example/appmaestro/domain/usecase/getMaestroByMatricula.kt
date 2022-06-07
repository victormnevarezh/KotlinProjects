package com.example.appmaestro.domain.usecase

import com.example.appmaestro.core.exception.Failure
import com.example.appmaestro.core.functional.Either
import com.example.appmaestro.core.interactor.UseCase
import com.example.appmaestro.data.dto.MaestroResponse
import com.example.appmaestro.domain.repository.MaestroRepository
import javax.inject.Inject

class getMaestroByMatricula @Inject constructor(private val maestroRepository : MaestroRepository) : UseCase<MaestroResponse, String>() {

    override suspend fun run(params: String): Either<Failure, MaestroResponse> {
        return maestroRepository.getMaestroByMatricula(params)
    }

}