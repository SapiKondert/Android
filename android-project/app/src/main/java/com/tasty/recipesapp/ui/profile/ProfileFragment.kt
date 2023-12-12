package com.tasty.recipesapp.ui.profile

import android.os.Bundle
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
import com.tasty.recipesapp.adapter.NewRecipesAdapter
import com.tasty.recipesapp.adapter.RecipesAdapter
import com.tasty.recipesapp.data.entities.RecipeEntity
import com.tasty.recipesapp.databinding.RecipeRowItemBinding
import com.tasty.recipesapp.ui.recipe.RecipeListViewModel
import com.tasty.recipesapp.databinding.FragmentProfileBinding
import androidx.navigation.fragment.findNavController
import com.tasty.recipesapp.data.models.NewRecipeModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    private val recipeViewModel : RecipeListViewModel by viewModels()
    private val recipesAdapter = NewRecipesAdapter(deleteClick = ::deleteClick,viewClicked = ::viewClicked)
    private lateinit var binder: FragmentProfileBinding
    private val profileViewModel : ProfileViewModel by viewModels()
    //private val binder: FragmentRecipesBinding = FragmentRecipesBinding.inflate(layoutInflater)

    fun deleteClick(recipe: RecipeEntity) {
        profileViewModel.removeRecipe(recipe)
    }

    fun viewClicked(data:NewRecipeModel){
        val navHostFragment = NavHostFragment.findNavController(this)
        val bundle = bundleOf("data" to data)
        navHostFragment.navigate(R.id.action_profileFragment_to_privateRecipeDetailFragmant,bundle)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("ProfileFragment", "Created")
        //val binder = RecipeRowItemBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binder.newRecipeButton.setOnClickListener{
            Log.i("ProfileFragment", "New Recipe Button Clicked")
            val navHostFragment = NavHostFragment.findNavController(this)
            navHostFragment.navigate(R.id.action_profileFragment_to_newRecipeFragment)
        }
        profileViewModel.getAll()
        profileViewModel.recipes.observe(viewLifecycleOwner) {  recipes ->
            recipesAdapter.updateData(recipes)
        }
        val recyclerView: RecyclerView = view.findViewById(R.id.new_recipes_recycler_view)
        recyclerView.adapter = recipesAdapter

//        recipeViewModel.loadData(requireContext())
//        recipeViewModel.recipeModel.observe(viewLifecycleOwner) {  recipes ->
//            recipesAdapter.updateData(recipes)
//        }
//        val recyclerView: RecyclerView = view.findViewById(R.id.recipes_recycler_view)
//        recyclerView.adapter = recipesAdapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binder = FragmentProfileBinding.inflate(inflater, container, false)
        return binder.root
    }
}