package com.engin.polymorphicserialization.domain.di

import com.engin.polymorphicserialization.data.usecase.GetSectionsUseCaseImpl
import com.engin.polymorphicserialization.domain.usecase.GetSectionsUseCase
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