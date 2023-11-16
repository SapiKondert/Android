package com.tasty.recipesapp.data.dtos

import com.google.gson.annotations.SerializedName

data class CreditDTO(
    @SerializedName("name")
    val name: String?,
    @SerializedName("type")
    val type: String
)
