package com.example.feature.domain.usecase

import androidx.paging.PagingConfig
import com.example.domain.data.repository.CharactersRepository
import com.example.domain.usecase.GetCharactersUseCase
import com.example.domain.usecase.GetCharactersUseCaseImpl
import com.example.testing.MainDispatcherRule
import com.example.testing.model.CharacterFactory
import com.example.testing.pagingsource.PagingSourceFactory
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class GetCharactersUseCaseImplTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule(StandardTestDispatcher())

    @Mock
    lateinit var repository: CharactersRepository

    private lateinit var userCase: GetCharactersUseCase

    private val person = CharacterFactory().create(CharacterFactory.Person.Rick)

    private val fakePagingSource = PagingSourceFactory().create(listOf(person))

    @Before
    fun setup() {
        userCase = GetCharactersUseCaseImpl(
            charactersRepository = repository
        )
    }

    @Test
    fun `should validate flow paging data creation when invoke from use case is called`() =
        runTest {
            whenever(repository.getCharacters(""))
                .thenReturn(fakePagingSource)

            val result =
                userCase.invoke(
                    GetCharactersUseCase.GetCharactersParams(
                        "",
                        PagingConfig(20)
                    )
                ).first()

            verify(repository).getCharacters("")

            assertNotNull(result)
        }
}
