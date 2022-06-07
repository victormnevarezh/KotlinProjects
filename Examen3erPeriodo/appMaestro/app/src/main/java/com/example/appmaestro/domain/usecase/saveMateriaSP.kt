package com.example.appmaestro.domain.usecase

import com.example.appmaestro.core.exception.Failure
import com.example.appmaestro.core.functional.Either
import com.example.appmaestro.core.interactor.UseCase
import com.example.appmaestro.domain.model.Materia
import com.example.appmaestro.domain.repository.MateriaRepository
import javax.inject.Inject

class saveMateriaSP @Inject constructor(private val materiaRepository : MateriaRepository) : UseCase<Boolean, Materia>() {

    override suspend fun run(params: Materia): Either<Failure, Boolean> {
        return materiaRepository.saveMateria(params)
    }

}