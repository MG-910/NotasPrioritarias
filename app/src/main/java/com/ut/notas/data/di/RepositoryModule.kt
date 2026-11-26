package com.ut.notas.data.di

import com.ut.notas.data.repository.NotaRepositoryImp
import com.ut.notas.domain.repository.NotaRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindNotaRepository(
        notaRepositoryImp: NotaRepositoryImp
    ): NotaRepository
}