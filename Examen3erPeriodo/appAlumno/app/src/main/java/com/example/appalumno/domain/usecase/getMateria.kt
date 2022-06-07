package com.example.appalumno.domain.usecase

import com.example.appalumno.core.exception.Failure
import com.example.appalumno.core.functional.Either
import com.example.appalumno.core.interactor.UseCase
import com.example.appalumno.data.dto.MateriaResponse
import com.example.appalumno.domain.repository.MateriaRepository
import javax.inject.Inject

class getMateria @Inject constructor(private val materiaRepository: MateriaRepository):
    UseCase<MateriaResponse,String>() {
    override suspend fun run(params: String): Either<Failure, MateriaResponse> {
        return materiaRepository.getMateria(params)
    }

}