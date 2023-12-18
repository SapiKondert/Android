package com.tasty.recipesapp.providers

import android.content.Context
import com.tasty.recipesapp.data.daos.FavoritesDao
import com.tasty.recipesapp.data.daos.RecipeDao
import com.tasty.recipesapp.data.databases.FavoritesDatabase
import com.tasty.recipesapp.data.databases.RecipeDatabase
import com.tasty.recipesapp.repository.RecipeRepository

object RepositoryProvider {
    private lateinit var recipeDao: RecipeDao
    private lateinit var favoritesDao: FavoritesDao

    fun initialize(context: Context) {
        recipeDao = RecipeDatabase.getDatabase(context).recipeDao()
        favoritesDao = FavoritesDatabase.getDatabase(context).favoritesDao()
    }

    val recipeRepository: RecipeRepository by lazy {
        checkInitialized()
        RecipeRepository(recipeDao, favoritesDao)
    }

    private fun checkInitialized() {
        if (!::recipeDao.isInitialized) {
            throw UninitializedPropertyAccessException("RepositoryProvider has not been initialized")
        }
        if(!::favoritesDao.isInitialized) {
            throw UninitializedPropertyAccessException("RepositoryProvider has not been initialized")
        }
    }
}