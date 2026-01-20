package com.example.domain.data.network.response

import com.google.gson.annotations.SerializedName

data class RickAndMortyResponse(
    @SerializedName("info")
    val info: PageResponse,
    @SerializedName("results")
    val results: CharacterResponse
)