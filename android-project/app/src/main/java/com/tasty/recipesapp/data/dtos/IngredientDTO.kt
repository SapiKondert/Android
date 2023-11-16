package com.tasty.recipesapp.data.dtos

import com.google.gson.annotations.SerializedName

data class IngredientDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("created_at")
    val createdAt: Int,
    @SerializedName("display_plural")
    val displayPlural: String,
    @SerializedName("display_singular")
    val displaySingular: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("updated_at")
    val updatedAt: String,
)
