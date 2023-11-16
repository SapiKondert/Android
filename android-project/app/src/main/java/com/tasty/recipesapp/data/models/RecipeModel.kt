package com.tasty.recipesapp.data.models

data class RecipeModel(
    val id:Int,
    val name:String,
    val instructions:List<InstructionModel>,
    val nutrition: NutritionModel,
    val credits:List<CreditModel>,
    val sections:List<SectionModel>,
    val tags:List<TagModel>,
    val topic:List<TopicModel>,
    val userRating:UserRatingModel
)
