package com.example.feature.framework.network.response

import com.google.gson.annotations.SerializedName

data class DataWrapperResponse(
    @SerializedName("info")
    val info: PageResponse,
    @SerializedName("results")
    val results: List<CharacterResponse>
)
