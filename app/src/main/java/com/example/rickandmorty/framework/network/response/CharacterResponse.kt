package com.example.rickandmorty.framework.network.response

import com.example.domain.model.CharacterData
import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("species")
    val species: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("origin")
    val origin: OriginCharacter,
    @SerializedName("location")
    val location: LocationCharacter,
    @SerializedName("image")
    val image: String,
    @SerializedName("episode")
    val episode: List<String>,
    @SerializedName("url")
    val url: String,
    @SerializedName("created")
    val created: String
)

fun CharacterResponse.toCharacterModel(): CharacterData =
    CharacterData(
        id = this.id,
        name = this.name,
        imageUrl = this.image,
    )
