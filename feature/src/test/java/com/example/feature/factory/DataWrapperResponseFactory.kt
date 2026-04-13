package com.example.feature.factory

import com.example.feature.framework.network.response.CharacterResponse
import com.example.feature.framework.network.response.DataWrapperResponse
import com.example.feature.framework.network.response.LocationCharacter
import com.example.feature.framework.network.response.OriginCharacter
import com.example.feature.framework.network.response.PageResponse

class DataWrapperResponseFactory {
    fun create() = DataWrapperResponse(
        info = PageResponse(
            count = 20,
            pages = 0,
            next = "",
            prev = ""
        ),
        results = listOf(
            CharacterResponse(
                id = 1,
                name = "Rick",
                status = "",
                species = "",
                type = "",
                gender = "",
                origin = OriginCharacter(
                    name = "",
                    url = ""
                ),
                location = LocationCharacter(
                    name = "",
                    url = ""
                ),
                image = "Rick",
                episode = emptyList(),
                url = "",
                created = ""
            ), CharacterResponse(
                id = 2,
                name = "Morty",
                status = "",
                species = "",
                type = "",
                gender = "",
                origin = OriginCharacter(
                    name = "",
                    url = ""
                ),
                location = LocationCharacter(
                    name = "",
                    url = ""
                ),
                image = "Morty",
                episode = emptyList(),
                url = "",
                created = ""
            )
        )
    )
}
