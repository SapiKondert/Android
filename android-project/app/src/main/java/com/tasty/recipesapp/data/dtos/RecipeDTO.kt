package com.tasty.recipesapp.data.dtos

import com.google.gson.annotations.SerializedName

data class RecipeDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("instructions")
    val instructions: List<InstructionDTO>,
    @SerializedName("nutrition")
    val nutrition: NutritionDTO,
    @SerializedName("credits")
    val credits: List<CreditDTO>,
    @SerializedName("sections")
    val sections: List<SectionDTO>,
    @SerializedName("tags")
    val tags: List<TagDTO>,
    @SerializedName("topics")
    val topics: List<TopicDTO>,
    @SerializedName("user_ratings")
    val userRating: UserRatingDTO
)
