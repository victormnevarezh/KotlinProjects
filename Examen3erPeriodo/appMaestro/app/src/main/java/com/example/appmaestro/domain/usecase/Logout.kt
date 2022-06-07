package com.example.appmaestro.domain.usecase

import com.example.appmaestro.core.exception.Failure
import com.example.appmaestro.core.functional.Either
import com.example.appmaestro.core.interactor.UseCase
import com.example.appmaestro.domain.repository.MaestroRepository
import javax.inject.Inject

class Logout @Inject constructor(private val maestroRepository: MaestroRepository) :
    UseCase<Boolean, UseCase.None>() {
    override suspend fun run(params: None): Either<Failure, Boolean> {
       return maestroRepository.doLogout()
    }

}