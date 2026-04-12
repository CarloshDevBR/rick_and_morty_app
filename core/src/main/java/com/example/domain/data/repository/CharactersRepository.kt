package com.example.domain.data.repository

import androidx.paging.PagingSource
import com.example.domain.model.CharacterData

interface CharactersRepository {
    fun getCharacters(query: String): PagingSource<Int, CharacterData>
}
