package com.tasty.recipesapp.data.dtos

import com.google.gson.annotations.SerializedName

data class TopicDTO(
    @SerializedName("name")
    val name: String,
    @SerializedName("slug")
    val slug: String
)
