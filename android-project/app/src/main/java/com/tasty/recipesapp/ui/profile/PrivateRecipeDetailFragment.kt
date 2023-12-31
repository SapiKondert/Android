package com.tasty.recipesapp.ui.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import com.tasty.recipesapp.R
import com.tasty.recipesapp.adapter.SimpleListAdapter
import com.tasty.recipesapp.data.models.NewRecipeModel
import com.tasty.recipesapp.databinding.FragmentPrivateRecipeDetailFragmantBinding
import com.tasty.recipesapp.databinding.FragmentProfileBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PrivateRecipeDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PrivateRecipeDetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var data: NewRecipeModel? = null
    private var id: Long? = null
    private val ingredientsListAdapter = SimpleListAdapter()
    private val instructionListAdapter = SimpleListAdapter()


    private lateinit var binder: FragmentPrivateRecipeDetailFragmantBinding
    private val privateRecipeViewModel : PrivateRecipeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        id = arguments?.getLong("data")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binder.instructionsRecyclerView.layoutManager = LinearLayoutManager(context)
        binder.ingredientsRecyclerView.layoutManager = LinearLayoutManager(context)
        binder.instructionsRecyclerView.adapter = instructionListAdapter
        binder.ingredientsRecyclerView.adapter = ingredientsListAdapter
        privateRecipeViewModel.getById(id!!)
        privateRecipeViewModel.recipe.observe(viewLifecycleOwner) {  recipe ->
            data = recipe
            binder.privateRecipeTitle.text = data?.title
            binder.descriptionTextView.text = data?.description
            val imageView  = binder.imageView
            val imageURL = data?.pictureURL
            Log.i("Image URL", "$imageURL")
            Glide.with(this)
                .load(imageURL)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .fallback(R.drawable.ic_launcher_foreground)
                .into(imageView)
            recipe.instructions?.let { instructionListAdapter.setData(it) }
            recipe.ingredients?.let { ingredientsListAdapter.setData(it) }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binder = FragmentPrivateRecipeDetailFragmantBinding.inflate(inflater, container, false)
        return binder.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PrivateRecipeDetailFragmant.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PrivateRecipeDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}