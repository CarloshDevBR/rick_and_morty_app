package com.example.rickandmorty.framework.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.domain.data.repository.CharactersRemoteDataSource
import com.example.domain.model.CharacterData
import com.example.rickandmorty.framework.network.response.DataWrapperResponse
import com.example.rickandmorty.framework.network.response.toCharacterModel

class CharactersPagingSource(
    private val remoteDataSource: CharactersRemoteDataSource<DataWrapperResponse>,
    private val query: String
) : PagingSource<Int, CharacterData>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterData> =
        try {
            val page = params.key ?: DEFAULT_START

            val queries = hashMapOf(
                "page" to page.toString()
            )

            if (query.isNotBlank()) {
                queries["name"] = query
            }

            val response = remoteDataSource.fetchCharacters(queries)

            val nextKey = response.info.next.let { nextUrl ->
                Regex("""[?&]page=(\d+)""")
                    .find(nextUrl)
                    ?.groupValues
                    ?.getOrNull(1)
                    ?.toInt()
            }

            LoadResult.Page(
                data = response.results.map { it.toCharacterModel() },
                prevKey = null,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    override fun getRefreshKey(state: PagingState<Int, CharacterData>): Int? =
        state.anchorPosition?.let { anchoPosition ->
            val anchorPage = state.closestPageToPosition(anchoPosition)
            anchorPage?.prevKey?.plus(LIMIT) ?: anchorPage?.nextKey?.minus(LIMIT)
        }

    private companion object {
        const val DEFAULT_START = 1
        const val LIMIT = 20
    }
}