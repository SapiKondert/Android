package com.tasty.recipesapp.repository

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tasty.recipesapp.data.API.RecipeApiClient
import com.tasty.recipesapp.data.daos.FavoritesDao
import com.tasty.recipesapp.data.daos.RecipeDao
import com.tasty.recipesapp.data.dtos.NewRecipeDTO
import com.tasty.recipesapp.data.dtos.RecipeDTO
import com.tasty.recipesapp.data.entities.FavoritesEntity
import com.tasty.recipesapp.data.entities.RecipeEntity
import com.tasty.recipesapp.data.models.NewRecipeModel
import com.tasty.recipesapp.data.models.RecipeModel
import com.tasty.recipesapp.utils.Mapping.toModel
import com.tasty.recipesapp.utils.Mapping.toRecipeModelList
import org.json.JSONObject
import java.io.IOException
import com.tasty.recipesapp.utils.Mapping
import com.tasty.recipesapp.utils.Mapping.toRecipeModel

class RecipeRepository(private val recipeDao: RecipeDao,private val favoritesDao: FavoritesDao) : IGenericRepository<RecipeModel> {

    private val gson = Gson()
    private val recipeApiClient = RecipeApiClient()


    suspend fun getRecipesFromApi(
        from: String,
        size: String,
        tags: String? = null,
    ): List<RecipeModel> {
        val response = recipeApiClient.getRecipes(from, size, tags)
        return response?.results?.map { it.toRecipeModel() } ?: emptyList()
    }


    suspend fun insertRecipe(recipe: RecipeEntity) {
        recipeDao.insertRecipe(recipe)
    }
    suspend fun getAllRecipes(): List<NewRecipeModel> {
        return recipeDao.getAllRecipes().map {
            val jsonObject = JSONObject(it.json)
            jsonObject.apply { put("id", it.internalId) }
            Log.i("RecipeRepository", jsonObject.toString())
            gson.fromJson(jsonObject.toString(), NewRecipeDTO::class.java).toModel()
        }
    }

    suspend fun deleteRecipe(recipe: RecipeEntity) {
        recipeDao.deleteRecipe(recipe)
    }

    override fun getAll(context: Context): List<RecipeModel> {
        return readAll(context).toRecipeModelList()
    }

    suspend fun getRecipeById(id: Long): NewRecipeModel {
        val r = recipeDao.getRecipeById(id)
        val jsonObject = JSONObject(r?.json)
        jsonObject.apply { put("id", r?.internalId) }
        return gson.fromJson(jsonObject.toString(), NewRecipeDTO::class.java).toModel()
    }


    suspend fun insertFavoriteRecipe(recipe: FavoritesEntity) {
        favoritesDao.insertRecipe(recipe)
    }

    suspend fun deleteFavoriteRecipe(recipe: FavoritesEntity) {
        favoritesDao.deleteRecipe(recipe)
    }

    suspend fun getAllFavorites(): List<FavoritesEntity> {
        return favoritesDao.getAllRecipes()
    }

    fun readAll(context: Context): List<RecipeDTO> {
        var recipeList = listOf<RecipeDTO>()
        val assetManager = context.assets
        try {
            val inputStream = assetManager.open("full.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            val jsonString = String(buffer, Charsets.UTF_8)

            //If there is an extra label
            val jsonObject = JSONObject(jsonString)
            val recipeArray = jsonObject.getJSONArray("results")

            val type = object : TypeToken<List<RecipeDTO>>() {}.type
            //if it is simple
            //val instructionList = gson.fromJson<List<InstructionDTO>>(jsonString, type)
            // if with label
            recipeList = gson.fromJson(recipeArray.toString(), type)
            //instructions.value = instructionList
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return recipeList
    }

}