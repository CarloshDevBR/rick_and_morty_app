package com.example.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.domain.data.repository.CharactersRepository
import com.example.domain.model.CharacterData
import com.example.domain.usecase.base.PagingUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val charactersRepository: CharactersRepository
) : PagingUseCase<GetCharactersUseCase.GetCharactersParams, CharacterData>() {
    override fun createFlowObservable(params: GetCharactersParams): Flow<PagingData<CharacterData>> =
        Pager(config = params.pagingConfig) {
            charactersRepository.getCharacters(params.query)
        }.flow

    data class GetCharactersParams(val query: String, val pagingConfig: PagingConfig)
}
