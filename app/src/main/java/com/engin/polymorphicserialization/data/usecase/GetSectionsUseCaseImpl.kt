package com.engin.polymorphicserialization.data.usecase

import com.engin.polymorphicserialization.data.remote.SectionApi
import com.engin.polymorphicserialization.data.dto.Section
import com.engin.polymorphicserialization.domain.usecase.GetSectionsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetSectionsUseCaseImpl @Inject constructor(
    private val api: SectionApi
) : GetSectionsUseCase {

    override fun invoke(): Flow<List<Section>> = flow {
        val response = api.getSections()
        if (response.isSuccessful)
            emit(response.body() ?: emptyList())
        else
            emit(emptyList())

    }.flowOn(Dispatchers.IO)
}