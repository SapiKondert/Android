package com.tasty.recipesapp.ui.recipe

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.tasty.recipesapp.R
import com.tasty.recipesapp.adapter.RecipesAdapter
import com.tasty.recipesapp.data.entities.FavoritesEntity
import com.tasty.recipesapp.data.models.RecipeModel
import com.tasty.recipesapp.databinding.FragmentRecipesBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RecipesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

// Milestone
class RecipesFragment : Fragment() {

    private val recipeViewModel : RecipeListViewModel by viewModels()
    private var allFavorites = mutableListOf<FavoritesEntity>()
    private var allRecipes = mutableListOf<RecipeModel>()
    private var favoritesToggled = false
    private var sortToggled = false
    private val recipesAdapter = RecipesAdapter(viewClicked = ::viewClicked,
        favoriteClicked = ::favoriteClicked,
        getAllFavorites = ::getAllFavorites)
    private lateinit var binder: FragmentRecipesBinding
    //private val binder: FragmentRecipesBinding = FragmentRecipesBinding.inflate(layoutInflater)

    fun viewClicked(data:RecipeModel){
        val navHostFragment = NavHostFragment.findNavController(this)
        Log.i("RecipesFragment2", data.name)
        val bundle = bundleOf("Title" to data.name, "Picture" to data.thumbnailURL)
        navHostFragment.navigate(R.id.action_recipesFragment_to_recipeDetailFragment,bundle)
    }

    fun favoriteClicked(data: RecipeModel){
        if (!allFavorites.any { it.recipeId == data.id }) {
            val entity = FavoritesEntity(recipeId = data.id)
            recipeViewModel.insertFavorite(entity)
        }
        else{
            var entity = allFavorites.find { it.recipeId == data.id }
            recipeViewModel.deleteFavorite(entity!!)
        }
        recipeViewModel.getFavorites()
    }

    fun getAllFavorites(): MutableList<FavoritesEntity>{
        recipeViewModel.getFavorites()
        return allFavorites
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //val binder = RecipeRowItemBinding.inflate(layoutInflater)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recipeViewModel.getFavorites()
        recipeViewModel.favorites.observe(viewLifecycleOwner) {  favorites ->
            allFavorites = favorites.toMutableList()
            Log.i("Favorites", favorites.toString())
        }
        binder.favorites.setOnClickListener{
            if (!favoritesToggled) {
                recipeViewModel.getFavorites()
                val ids = allFavorites.map { it.recipeId }
                recipesAdapter.updateData(allRecipes.filter { it.id in ids })
                favoritesToggled = true
            }
            else{
                recipesAdapter.updateData(allRecipes)
                favoritesToggled = false
            }
        }
        binder.sort.setOnClickListener{
            if (!sortToggled) {
                recipesAdapter.updateData(allRecipes.sortedBy { it.name })
                sortToggled = true
            }
            else{
                recipesAdapter.updateData(allRecipes)
                sortToggled = false
            }
        }
        binder.search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // This method is called to notify you that characters within `s` are about to be replaced.
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // This method is called to notify you that characters within `s` have been replaced.
            }

            override fun afterTextChanged(s: Editable?) {
                // This method is called to notify you that the characters within `s` have been changed.
                val editedText = s.toString()
                // Do something with the edited text
                if (!editedText.isEmpty()) {
                    recipesAdapter.updateData(allRecipes.filter { it.name.contains(editedText) })
                }
                else
                    recipesAdapter.updateData(allRecipes)
            }
        })
        recipeViewModel.loadAPIData()
        recipeViewModel.ApiData.observe(viewLifecycleOwner) {  recipes ->
            allRecipes = recipes.toMutableList();
            recipesAdapter.updateData(recipes)
        }
        val recyclerView: RecyclerView = view.findViewById(R.id.recipes_recycler_view)
        recyclerView.adapter = recipesAdapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binder = FragmentRecipesBinding.inflate(inflater, container, false)
        return binder.root
    }

}