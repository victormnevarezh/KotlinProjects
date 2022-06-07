package com.example.appmaestro.domain.repository

import com.example.appmaestro.core.exception.Failure
import com.example.appmaestro.core.functional.Either
import com.example.appmaestro.data.dto.MaestroResponse
import com.example.appmaestro.domain.model.Maestro
import com.example.appmaestro.domain.model.MaestroUpdt

interface MaestroRepository {
    fun getMaestroByMatricula (matricula : String) : Either<Failure, MaestroResponse>
    fun updateMaestro (matricula : String, maestro:MaestroUpdt) : Either<Failure, MaestroResponse>
    fun saveMaestro (maestro : Maestro) : Either<Failure, Boolean>
    fun doLogout(): Either<Failure, Boolean>
}