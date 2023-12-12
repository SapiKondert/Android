package com.tasty.recipesapp.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasty.recipesapp.data.entities.RecipeEntity
import com.tasty.recipesapp.data.models.NewRecipeModel
import com.tasty.recipesapp.data.models.RecipeModel
import com.tasty.recipesapp.providers.RepositoryProvider
import kotlinx.coroutines.launch

class ProfileViewModel: ViewModel() {

    public data class RecipeData(
        var title: String,
        var description: String,
        var pictureUrl:String,
        var ingredients:List<String>,
        var instructions:List<String>
    )

    private val _recipes = MutableLiveData<List<NewRecipeModel>>()
    val recipes: LiveData<List<NewRecipeModel>> = _recipes

    fun insertRecipe(recipe: RecipeEntity) {
        viewModelScope.launch {
            RepositoryProvider.recipeRepository.insertRecipe(recipe)
        }
    }

    fun getAll() {
        viewModelScope.launch {
            val r = RepositoryProvider.recipeRepository.getAllRecipes()
            _recipes.value = r
        }
    }

    fun removeRecipe(recipe: RecipeEntity) {
        viewModelScope.launch {
            RepositoryProvider.recipeRepository.deleteRecipe(recipe)
        }
    }


}