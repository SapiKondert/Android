package com.tasty.recipesapp.data.dtos

import com.google.gson.annotations.SerializedName

data class TagDTO(
    @SerializedName("display_name")
    val displayName: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("root_tag_type")
    val rootTagType: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("id")
    val id: Int
)
