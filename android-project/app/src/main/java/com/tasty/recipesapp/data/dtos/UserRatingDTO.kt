package com.tasty.recipesapp.data.dtos

import com.google.gson.annotations.SerializedName

data class UserRatingDTO(
    @SerializedName("count_positive")
    val countPositive: Int,
    @SerializedName("score")
    val score: Double,
    @SerializedName("count_negative")
    val countNegative: Int
)
