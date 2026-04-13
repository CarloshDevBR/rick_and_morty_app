package com.example.rickandmorty.framework.paging

import androidx.paging.PagingSource
import com.example.domain.data.repository.CharactersRemoteDataSource
import com.example.domain.model.CharacterData
import com.example.rickandmorty.factory.DataWrapperResponseFactory
import com.example.rickandmorty.framework.network.response.DataWrapperResponse
import com.example.testing.MainDispatcherRule
import com.example.testing.model.CharacterFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class CharactersPagingSourceTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainDispatcherRule()

    @Mock
    lateinit var remoteDataSource: CharactersRemoteDataSource<DataWrapperResponse>

    private val dataWrapperResponseFactory = DataWrapperResponseFactory()

    private val characterFactory = CharacterFactory()

    private lateinit var charactersPagingSource: CharactersPagingSource

    @Before
    fun setup() {
        charactersPagingSource = CharactersPagingSource(remoteDataSource, "")
    }

    @Test
    fun `should return a success load result when load is called`() = runTest {
        // ARRANGE
        whenever(remoteDataSource.fetchCharacters(any()))
            .thenReturn(dataWrapperResponseFactory.create())

        // ACT
        val result = charactersPagingSource.load(
            PagingSource.LoadParams.Refresh(
                null,
                loadSize = 2,
                false
            )
        )

        // ASSERT
        val expected = listOf(
            characterFactory.create(CharacterFactory.Person.Rick),
            characterFactory.create(CharacterFactory.Person.Morty)
        )

        assertEquals(
            PagingSource.LoadResult.Page(
                data = expected,
                prevKey = null,
                nextKey = null
            ),
            result
        )
    }

    @Test
    fun `should return a error load result when load is called`() = runTest {
        // ARRANGE
        val exception = RuntimeException()
        whenever(remoteDataSource.fetchCharacters((any())))
            .thenThrow(exception)

        // ACT
        val result = charactersPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        // ASSERT
        assertEquals(
            PagingSource.LoadResult.Error<Int, CharacterData>(exception),
            result
        )
    }
}
