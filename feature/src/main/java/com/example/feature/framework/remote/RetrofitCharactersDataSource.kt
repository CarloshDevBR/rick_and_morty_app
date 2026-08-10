package com.example.feature.framework.remote

import com.example.domain.data.repository.CharactersRemoteDataSource
import com.example.feature.framework.network.RickAndMortyApi
import com.example.feature.framework.network.response.DataWrapperResponse
import javax.inject.Inject

class RetrofitCharactersDataSource @Inject constructor(
    private val api: RickAndMortyApi
) : CharactersRemoteDataSource<DataWrapperResponse> {
    override suspend fun fetchCharacters(queries: Map<String, String>): DataWrapperResponse =
        api.getCharacters(queries)
}
