package com.tasty.recipesapp.data.API

import com.tasty.recipesapp.data.dtos.RecipeDTO
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RecipeService {

    data class RecipeResponse(
        val results: List<RecipeDTO>
    )

    @GET("recipes/list")
    @Headers(
        "X-RapidAPI-Key: acf8fba0bdmsha31bac73b6bbc91p152ec4jsn8dadfa2b27b8",
        "X-RapidAPI-Host: tasty.p.rapidapi.com"
    )
    suspend fun getRecipes(
        @Query("from") from: String,
        @Query("size") size: String,
        @Query("tags") tags: String? = null
    ): RecipeResponse
}