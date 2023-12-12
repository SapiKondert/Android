package com.tasty.recipesapp.ui.recipe

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
import com.tasty.recipesapp.adapter.RecipesAdapter
import com.tasty.recipesapp.data.models.RecipeModel
import com.tasty.recipesapp.databinding.FragmentRecipesBinding
import com.tasty.recipesapp.databinding.RecipeRowItemBinding

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
    private val recipesAdapter = RecipesAdapter(viewClicked = ::viewClicked)

    //private val binder: FragmentRecipesBinding = FragmentRecipesBinding.inflate(layoutInflater)

    fun viewClicked(data:RecipeModel){
        val navHostFragment = NavHostFragment.findNavController(this)
        Log.i("RecipesFragment2", data.name)
        val bundle = bundleOf("Title" to data.name, "Picture" to data.thumbnailURL)
        navHostFragment.navigate(R.id.action_recipesFragment_to_recipeDetailFragment,bundle)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binder = RecipeRowItemBinding.inflate(layoutInflater)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recipeViewModel.loadData(requireContext())
        recipeViewModel.recipeModel.observe(viewLifecycleOwner) {  recipes ->
            for (item in recipes){
                Log.i("RecipesFragment", item.name)
            }
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

        return inflater.inflate(R.layout.fragment_recipes, container, false)
    }

}