package com.tasty.recipesapp.data.models

data class UserRatingModel(
    val countPositive: Int,
    val score: Double,
    val countNegative: Int
)
