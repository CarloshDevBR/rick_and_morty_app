package com.example.feature.di

import com.example.feature.framework.di.qualifier.BaseUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object BaseUrlTestModule {
    private const val BASE_URL = "http://localhost:8080/"

    @BaseUrl
    @Provides
    fun provideBaseUrl(): String = BASE_URL
}
