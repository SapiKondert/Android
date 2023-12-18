package com.tasty.recipesapp.ui.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasty.recipesapp.data.entities.RecipeEntity
import com.tasty.recipesapp.data.models.NewRecipeModel
import com.tasty.recipesapp.providers.RepositoryProvider
import kotlinx.coroutines.launch
import kotlinx.coroutines.CompletableDeferred

class PrivateRecipeViewModel: ViewModel() {

    private val _recipe = MutableLiveData<NewRecipeModel>()
    val recipe: LiveData<NewRecipeModel> = _recipe
    fun getById(id: Long){
        viewModelScope.launch {
            val r = RepositoryProvider.recipeRepository.getRecipeById(id)
            _recipe.value = r
            Log.i("PrivateRecipeViewModel", r.toString())
        }
    }
}