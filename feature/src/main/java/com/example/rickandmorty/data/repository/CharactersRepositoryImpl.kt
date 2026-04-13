package com.example.rickandmorty.data.repository

import androidx.paging.PagingSource
import com.example.domain.data.repository.CharactersRemoteDataSource
import com.example.domain.data.repository.CharactersRepository
import com.example.domain.model.CharacterData
import com.example.rickandmorty.framework.network.response.DataWrapperResponse
import com.example.rickandmorty.framework.paging.CharactersPagingSource
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val remoteDataSource: CharactersRemoteDataSource<DataWrapperResponse>
) : CharactersRepository {
    override fun getCharacters(query: String): PagingSource<Int, CharacterData> =
        CharactersPagingSource(
            remoteDataSource = remoteDataSource,
            query = query
        )
}
