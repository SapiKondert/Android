package com.tasty.recipesapp.data.dtos

import com.google.gson.annotations.SerializedName
data class NewRecipeDTO(
    @SerializedName("id")
    val id: Long,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("pictureUrl")
    val pictureURL: String,
    @SerializedName("instructions")
    val instructions: List<String>,
    @SerializedName("ingredients")
    val ingredients: List<String>
)
