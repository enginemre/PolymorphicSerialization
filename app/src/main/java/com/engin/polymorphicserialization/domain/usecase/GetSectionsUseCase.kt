package com.engin.polymorphicserialization.domain.usecase

import com.engin.polymorphicserialization.data.dto.Section
import kotlinx.coroutines.flow.Flow

interface GetSectionsUseCase {
    operator fun invoke () : Flow<List<Section>>
}