package com.tasty.recipesapp.ui.recipe

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.tasty.recipesapp.R
import com.tasty.recipesapp.adapter.SimpleListAdapter
import com.tasty.recipesapp.data.models.NewRecipeModel
import com.tasty.recipesapp.databinding.FragmentPrivateRecipeDetailFragmantBinding
import com.tasty.recipesapp.databinding.FragmentRecipeDetailBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RecipeDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecipeDetailFragment : Fragment() {

    private lateinit var binder: FragmentRecipeDetailBinding
    private var title: String? = null
    private var picture: String? = null
    private var description: String? = null
    private var ingredients: List<String>? = null
    private var instructions: List<String>? = null
    private val ingredientsListAdapter = SimpleListAdapter()
    private val instructionListAdapter = SimpleListAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = arguments?.getString("Title")
        picture = arguments?.getString("Picture")
        description = arguments?.getString("Description")
        ingredients = arguments?.getStringArrayList("Ingredients")?.toList()
        instructions = arguments?.getStringArrayList("Instructions")?.toList()
        Log.i("Picture", "$picture")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binder.instructionsRecyclerView.layoutManager = LinearLayoutManager(context)
        binder.ingredientsRecyclerView.layoutManager = LinearLayoutManager(context)
        binder.instructionsRecyclerView.adapter = instructionListAdapter
        binder.ingredientsRecyclerView.adapter = ingredientsListAdapter
        binder.recipeTitle.text = title.toString()
        val imageView  = binder.imageView
        val imageURL = picture
        Log.i("Image URL", "$imageURL")
        Glide.with(this)
            .load(imageURL)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_foreground)
            .fallback(R.drawable.ic_launcher_foreground)
            .into(imageView)
        binder.description.text = description
        Log.i("Instructions", "$instructions")
        Log.i("Ingredients", "$ingredients")
        instructionListAdapter.setData(instructions?: emptyList())
        ingredientsListAdapter.setData(ingredients?: emptyList())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binder = FragmentRecipeDetailBinding.inflate(inflater, container, false)
        return binder.root
    }
}