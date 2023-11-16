package com.tasty.recipesapp.data.dtos

import com.google.gson.annotations.SerializedName

data class NutritionDTO(
    @SerializedName("fiber")
    val fiber: Int,
    @SerializedName("protein")
    val protein: Int,
    @SerializedName("fat")
    val fat: Int,
    @SerializedName("calories")
    val calories: Int,
    @SerializedName("sugar")
    val sugar: Int,
    @SerializedName("carbohydrates")
    val carbohydrates: Int,
    @SerializedName("start_time")
    val startTime: Int
)
