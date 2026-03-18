package com.example.testing.model

import com.example.domain.model.CharacterData

class CharacterFactory {
    fun create(person: Person) = when (person) {
        Person.Rick -> CharacterData(
            id = 1,
            name = "Rick",
            imageUrl = "Rick"
        )

        Person.Morty -> CharacterData(
            id = 2,
            name = "Morty",
            imageUrl = "Morty"
        )
    }

    sealed class Person {
        object Rick : Person()
        object Morty : Person()
    }
}
