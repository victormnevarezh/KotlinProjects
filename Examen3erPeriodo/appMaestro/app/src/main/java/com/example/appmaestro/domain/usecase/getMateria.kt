package com.example.appmaestro.domain.usecase

import com.example.appmaestro.core.exception.Failure
import com.example.appmaestro.core.functional.Either
import com.example.appmaestro.core.interactor.UseCase
import com.example.appmaestro.data.dto.MateriaResponse
import com.example.appmaestro.domain.repository.MateriaRepository
import javax.inject.Inject

class getMateria @Inject constructor(private val materiaRepository : MateriaRepository) : UseCase<MateriaResponse, String>() {

    override suspend fun run(params: String): Either<Failure, MateriaResponse> {
        return materiaRepository.getMateria(params)
    }

}