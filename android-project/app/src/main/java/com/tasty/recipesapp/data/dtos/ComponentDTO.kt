package com.tasty.recipesapp.data.dtos

import com.google.gson.annotations.SerializedName

data class ComponentDTO (
    @SerializedName("extra_comment")
    val extraComment: String,
    @SerializedName("ingredient")
    val ingredient: IngredientDTO,
    @SerializedName("id")
    val id: Int,
    @SerializedName("position")
    val position: Int,
    @SerializedName("measurements")
    val measurements: List<MeasurementDTO>,
    @SerializedName("raw_text")
    val rawText: String
)