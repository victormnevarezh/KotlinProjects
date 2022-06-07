package com.example.appalumno.domain.repository

import com.example.appalumno.core.exception.Failure
import com.example.appalumno.core.functional.Either
import com.example.appalumno.data.dto.MateriaResponse

interface MateriaRepository {
    fun getMateria(nombre: String): Either<Failure, MateriaResponse>
}