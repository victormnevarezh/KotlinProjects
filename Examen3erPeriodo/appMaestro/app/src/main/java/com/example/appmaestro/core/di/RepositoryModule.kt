package com.example.appmaestro.core.di

import com.example.appmaestro.core.plataform.NetworkHandler
import com.example.appmaestro.core.platform.AuthManager
import com.example.appmaestro.data.api.AlumnoApi
import com.example.appmaestro.data.api.MaestroApi
import com.example.appmaestro.data.api.MateriaApi
import com.example.appmaestro.data.source.AlumnoRepositoryImpl
import com.example.appmaestro.data.source.MaestroRepositoryImpl
import com.example.appmaestro.data.source.MateriaRepositoryImpl
import com.example.appmaestro.domain.repository.AlumnoRepository
import com.example.appmaestro.domain.repository.MaestroRepository
import com.example.appmaestro.domain.repository.MateriaRepository
import com.example.appmaestro.framework.api.ApiProvider
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
    fun provideMaestroRepository(
        apiProvider: ApiProvider,
        authManager:AuthManager,
        networkHandler: NetworkHandler
    ) : MaestroRepository = MaestroRepositoryImpl(apiProvider.getEndPoint(MaestroApi::class.java),authManager,networkHandler)

    @Provides
    @Singleton
    fun provideMateriaRepository(
        apiProvider: ApiProvider,
        authManager: AuthManager,
        networkHandler: NetworkHandler
    ) : MateriaRepository = MateriaRepositoryImpl(apiProvider.getEndPoint(MateriaApi::class.java),authManager,networkHandler)

    @Provides
    @Singleton
    fun provideAlumnoRepository(
        apiProvider: ApiProvider,
        networkHandler: NetworkHandler
    ) : AlumnoRepository = AlumnoRepositoryImpl(apiProvider.getEndPoint(AlumnoApi::class.java),networkHandler)
}