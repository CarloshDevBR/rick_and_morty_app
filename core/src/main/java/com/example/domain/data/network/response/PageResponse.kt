package com.example.domain.data.network.response

import com.google.gson.annotations.SerializedName

data class PageResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("prev")
    val prev: String
)