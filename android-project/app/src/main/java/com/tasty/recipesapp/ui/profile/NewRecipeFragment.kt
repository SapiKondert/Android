package com.tasty.recipesapp.ui.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.google.gson.Gson
import com.tasty.recipesapp.R
import com.tasty.recipesapp.data.entities.RecipeEntity
import com.tasty.recipesapp.databinding.FragmentNewRecipeBinding
import com.tasty.recipesapp.repository.RecipeRepository

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NewRecipeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewRecipeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binder: FragmentNewRecipeBinding
    private val gson = Gson()
    private val profileViewModel : ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binder.submitButton.setOnClickListener{
            val recipe = ProfileViewModel.RecipeData(
                title = binder.titleEditText.text.toString(),
                description = binder.descriptionEditText.text.toString(),
                pictureUrl = binder.pictureUrlEditText.text.toString(),
                instructions = listOf(
                    binder.instruction1EditText.text.toString(),
                    binder.instruction2EditText.text.toString(),
                    binder.instruction3EditText.text.toString()
                ),
                ingredients = listOf(
                    binder.ingredient1EditText.text.toString(),
                    binder.ingredient2EditText.text.toString(),
                    binder.ingredient3EditText.text.toString()
                )
            )
            val recipeEntity = RecipeEntity(
                json = gson.toJson(recipe)
            )
            profileViewModel.insertRecipe(recipeEntity)
            Log.i("New Recipe", "$recipeEntity")
            val navHostFragment = NavHostFragment.findNavController(this)
            navHostFragment.navigate(R.id.action_newRecipeFragment_to_profileFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binder = FragmentNewRecipeBinding.inflate(inflater, container, false)
        return binder.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NewRecipeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NewRecipeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}