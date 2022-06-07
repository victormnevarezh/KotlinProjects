package com.example.appmaestro.domain.usecase

import com.example.appmaestro.core.exception.Failure
import com.example.appmaestro.core.functional.Either
import com.example.appmaestro.core.interactor.UseCase
import com.example.appmaestro.domain.model.Maestro
import com.example.appmaestro.domain.repository.MaestroRepository
import javax.inject.Inject

class saveMaestroSP @Inject constructor(private val MaestroRepository: MaestroRepository) :
    UseCase<Boolean, Maestro>() {
    override suspend fun run(params: Maestro): Either<Failure, Boolean> {
        return MaestroRepository.saveMaestro(params)
    }

}