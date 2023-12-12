package com.tasty.recipesapp.providers

import android.content.Context
import com.tasty.recipesapp.data.daos.RecipeDao
import com.tasty.recipesapp.data.databases.RecipeDatabase
import com.tasty.recipesapp.repository.RecipeRepository

object RepositoryProvider {
    private lateinit var recipeDao: RecipeDao

    fun initialize(context: Context) {
        recipeDao = RecipeDatabase.getDatabase(context).recipeDao()
    }

    val recipeRepository: RecipeRepository by lazy {
        checkInitialized()
        RecipeRepository(recipeDao)
    }

    private fun checkInitialized() {
        if (!::recipeDao.isInitialized) {
            throw UninitializedPropertyAccessException("RepositoryProvider has not been initialized")
        }
    }
}