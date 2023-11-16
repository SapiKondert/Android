package com.tasty.recipesapp.data.models

import com.tasty.recipesapp.data.dtos.IngredientDTO

data class ComponentModel(
    val extraComment: String,
    val ingredient: IngredientModel,
    val id: Int,
    val position: Int,
    val measurements: List<MeasurementModel>,
    val rawText: String
)
