package com.example.rickandmorty.presentation.characters

import androidx.paging.PagingData
import com.example.domain.usecase.GetCharactersUseCase
import com.example.testing.MainDispatcherRule
import com.example.testing.model.CharacterFactory
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class CharactersViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule(StandardTestDispatcher())

    @Mock
    lateinit var userCase: GetCharactersUseCase

    private lateinit var viewModel: CharactersViewModel

    private val pagingDataCharacters = PagingData.from(
        listOf(
            CharacterFactory().create(CharacterFactory.Person.Morty),
            CharacterFactory().create(CharacterFactory.Person.Rick)
        )
    )

    @Before
    fun setup() {
        viewModel = CharactersViewModel(userCase)
    }

    @Test
    fun `should validate the paging data object values when calling charactersPagingData`() =
        runTest {
            // ARRANGE
            whenever(userCase.invoke(any()))
                .thenReturn(flowOf(pagingDataCharacters))

            // ACT
            val result = viewModel.charactersPagingData("")

            // ASSERT
            assertNotNull(result)
        }

    @Test(expected = RuntimeException::class)
    fun `should throw an exception when the calling to the use case returns an exception`() =
        runTest {
            whenever(userCase.invoke(any()))
                .thenThrow(RuntimeException("erro"))

            viewModel.charactersPagingData("").first()
        }
}
