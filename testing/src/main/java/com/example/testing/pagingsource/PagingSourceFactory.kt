package com.example.testing.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.domain.model.CharacterData

class PagingSourceFactory {
    fun create(persons: List<CharacterData>) = object : PagingSource<Int, CharacterData>() {
        override fun getRefreshKey(state: PagingState<Int, CharacterData>): Int = 1

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterData> =
            LoadResult.Page(
                data = persons,
                prevKey = null,
                nextKey = 20
            )
    }
}