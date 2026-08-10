package com.example.feature.framework.network

import com.example.feature.framework.network.response.DataWrapperResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface RickAndMortyApi {
    @GET("character")
    suspend fun getCharacters(
        @QueryMap
        queries: Map<String, String>
    ): DataWrapperResponse
}
