package com.example.appmaestro.domain.repository

import com.example.appmaestro.core.exception.Failure
import com.example.appmaestro.core.functional.Either
import com.example.appmaestro.data.dto.MateriaResponse
import com.example.appmaestro.domain.model.Materia

interface MateriaRepository {

    fun getMateria (nombre : String) : Either<Failure, MateriaResponse>

    fun saveMateria (materia : Materia) : Either<Failure, Boolean>

}