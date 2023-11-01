package com.engin.polymorphicserialization.domain

import com.engin.polymorphicserialization.data.usecase.GetSectionsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DomainModule {

    @Binds
    abstract fun bindGetSectionUseCase(getSectionsUseCaseImpl: GetSectionsUseCaseImpl) : GetSectionsUseCase

}