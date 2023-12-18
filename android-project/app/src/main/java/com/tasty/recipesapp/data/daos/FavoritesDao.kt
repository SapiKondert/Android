package com.tasty.recipesapp.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.tasty.recipesapp.data.entities.FavoritesEntity
import com.tasty.recipesapp.data.entities.RecipeEntity

@Dao
interface FavoritesDao {
    @Insert
    suspend fun insertRecipe(recipe: FavoritesEntity)

    @Query("SELECT * FROM favorite")
    suspend fun getAllRecipes(): List<FavoritesEntity>
    @Delete
    suspend fun deleteRecipe(recipe: FavoritesEntity)
}