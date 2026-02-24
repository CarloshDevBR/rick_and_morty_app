package com.example.rickandmorty.presentation.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.model.CharacterData
import com.example.domain.usecase.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {
    fun charactersPagingData(query: String): Flow<PagingData<CharacterData>> =
        getCharactersUseCase.invoke(
            GetCharactersUseCase.GetCharactersParams(
                query = query,
                pagingConfig = getPageConfig()
            )
        ).cachedIn(viewModelScope)

    private fun getPageConfig() = PagingConfig(
        pageSize = LIMIT
    )

    private companion object {
        const val LIMIT = 20
    }
}
