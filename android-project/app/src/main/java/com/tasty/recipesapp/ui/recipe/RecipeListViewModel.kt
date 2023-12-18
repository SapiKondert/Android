package com.tasty.recipesapp.ui.recipe


import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasty.recipesapp.data.entities.FavoritesEntity
import com.tasty.recipesapp.data.models.InstructionModel
import com.tasty.recipesapp.data.models.NewRecipeModel
import com.tasty.recipesapp.data.models.RecipeModel
import com.tasty.recipesapp.providers.RepositoryProvider
import kotlinx.coroutines.launch

class RecipeListViewModel : ViewModel() {

    // LiveData to hold the list of InstructionModels
    // This should be changed to recipes
    private val _instructionModels = MutableLiveData<List<InstructionModel>>()
    val instructionModels: LiveData<List<InstructionModel>> = _instructionModels

    private val _recipeModels = MutableLiveData<List<RecipeModel>>()
    val recipeModel: LiveData<List<RecipeModel>> = _recipeModels

    private val _apiData = MutableLiveData<List<RecipeModel>>()
    val ApiData: LiveData<List<RecipeModel>> = _apiData

    private val _favorites = MutableLiveData<List<FavoritesEntity>>()
    val favorites: LiveData<List<FavoritesEntity>> = _favorites

    // Function to load data from the repository
    // Context should be removed
    fun loadData(context: Context) {
        val data = RepositoryProvider.recipeRepository.getAll(context)
        _recipeModels.value = data
    }

    fun loadAPIData() {
        viewModelScope.launch {
            _apiData.value = RepositoryProvider.recipeRepository.getRecipesFromApi("0", "15");
        }
    }

    fun insertFavorite(recipe: FavoritesEntity) {
        viewModelScope.launch {
            RepositoryProvider.recipeRepository.insertFavoriteRecipe(recipe)
        }
    }

    fun getFavorites() {
        viewModelScope.launch {
            _favorites.value = RepositoryProvider.recipeRepository.getAllFavorites()
        }
    }

    fun deleteFavorite(recipe: FavoritesEntity) {
        viewModelScope.launch {
            RepositoryProvider.recipeRepository.deleteFavoriteRecipe(recipe)
        }
    }

}