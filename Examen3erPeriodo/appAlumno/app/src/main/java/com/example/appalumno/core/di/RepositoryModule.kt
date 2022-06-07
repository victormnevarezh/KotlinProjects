package com.example.appalumno.core.di

import com.example.appalumno.core.plataform.NetworkHandler
import com.example.appalumno.core.platform.AuthManager
import com.example.appalumno.data.api.AlumnoApi
import com.example.appalumno.data.api.MateriaApi
import com.example.appalumno.data.source.AlumnoRepositoryImpl
import com.example.appalumno.data.source.MateriaRepositoryImpl
import com.example.appalumno.domain.repository.AlumnoRepository
import com.example.appalumno.domain.repository.MateriaRepository
import com.example.appalumno.framework.api.ApiProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideAlumnoRepository(
        apiProvider: ApiProvider,
        authManager: AuthManager,
        networkHandler: NetworkHandler
    ) : AlumnoRepository = AlumnoRepositoryImpl(apiProvider.getEndPoint(AlumnoApi::class.java),
        authManager,networkHandler)

    @Provides
    @Singleton
    fun provideMateriaRepository(
        apiProvider: ApiProvider,
        networkHandler: NetworkHandler
    ) : MateriaRepository = MateriaRepositoryImpl(apiProvider.getEndPoint(MateriaApi::class.java),networkHandler)

}