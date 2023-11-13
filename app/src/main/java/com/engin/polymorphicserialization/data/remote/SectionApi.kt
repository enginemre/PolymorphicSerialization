package com.engin.polymorphicserialization.data.remote

import com.engin.polymorphicserialization.data.dto.Section
import retrofit2.Response
import retrofit2.http.GET

interface SectionApi {

    @GET("enginemre/PolymorphicSerialization/master/api/sections.json")
    suspend fun getSections() : Response<List<Section>>
}