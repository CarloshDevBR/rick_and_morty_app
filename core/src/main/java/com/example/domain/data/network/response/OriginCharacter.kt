package com.example.domain.data.network.response

import com.google.gson.annotations.SerializedName

data class OriginCharacter(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)