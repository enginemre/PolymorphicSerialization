package com.engin.polymorphicserialization.data.di

import com.engin.polymorphicserialization.data.remote.SectionApi
import com.engin.polymorphicserialization.data.dto.Section
import com.engin.polymorphicserialization.util.Constants
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun providesPostJsonSerializer(): Converter.Factory {
        val modules = SerializersModule {
            polymorphic(Section::class) {
                subclass(Section.Image::class, Section.Image.serializer())
                subclass(Section.Articles::class, Section.Articles.serializer())
                subclass(Section.Info::class, Section.Info.serializer())
            }
        }
        val contentType = "application/json".toMediaType()
        val json =  Json {
            classDiscriminator = "type"
            serializersModule = modules
            ignoreUnknownKeys = true
            prettyPrint = true
            isLenient = true
            explicitNulls = false
        }
        return json.asConverterFactory(contentType)
    }


    @Provides
    @Singleton
    fun provideGeneralApi(client: OkHttpClient,converterFactory: Converter.Factory): SectionApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(converterFactory)
            .client(client)
            .build()
            .create()
    }


    @Provides
    @Singleton
    fun provideHttpLogger(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(loggingInterceptor)
            .build()
    }



}