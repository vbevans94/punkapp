package com.punkapp.api

import com.google.gson.annotations.SerializedName

data class ApiBeer(
    @SerializedName("id")
    val id: String,
    @SerializedName("tagline")
    val name: String,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("description")
    val description: String
)