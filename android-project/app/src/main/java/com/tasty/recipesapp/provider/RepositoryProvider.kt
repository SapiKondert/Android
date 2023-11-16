package com.tasty.recipesapp.providers

import com.tasty.recipesapp.repository.RecipeRepository

object RepositoryProvider {
    val recipeRepository: RecipeRepository = RecipeRepository()
}