package com.example.rickandmorty.framework.network.response

import com.google.gson.annotations.SerializedName

data class LocationCharacter(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)