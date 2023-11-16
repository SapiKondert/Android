package com.tasty.recipesapp.data.dtos

import com.google.gson.annotations.SerializedName

data class MeasurementDTO(
    @SerializedName("unit")
    val unit: UnitDTO,
    @SerializedName("quantity")
    val quantity: String,
    @SerializedName("id")
    val id: Int
)
