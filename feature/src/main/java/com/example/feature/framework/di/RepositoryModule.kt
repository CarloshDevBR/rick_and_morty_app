package com.example.feature.framework.di

import com.example.domain.data.repository.CharactersRemoteDataSource
import com.example.domain.data.repository.CharactersRepository
import com.example.feature.data.repository.CharactersRepositoryImpl
import com.example.feature.framework.network.response.DataWrapperResponse
import com.example.feature.framework.remote.RetrofitCharactersDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindCharacterRepository(repository: CharactersRepositoryImpl): CharactersRepository

    @Binds
    fun bindRemoteDataSource(dataSource: RetrofitCharactersDataSource): CharactersRemoteDataSource<DataWrapperResponse>
}
