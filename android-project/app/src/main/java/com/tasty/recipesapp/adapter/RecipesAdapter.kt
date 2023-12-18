package com.tasty.recipesapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tasty.recipesapp.R
import com.tasty.recipesapp.data.entities.FavoritesEntity
import com.tasty.recipesapp.data.models.NewRecipeModel
import com.tasty.recipesapp.data.models.RecipeModel

class RecipesAdapter(val viewClicked: (RecipeModel) -> Unit,
                     val favoriteClicked: (RecipeModel) -> Unit,
                     val getAllFavorites: () -> MutableList<FavoritesEntity>)
    : RecyclerView.Adapter<RecipesAdapter.ViewHolder>() {

    private val dataSet = mutableListOf<RecipeModel>()
    private val newDataSet = mutableListOf<NewRecipeModel>()
    private var allFavorites = mutableListOf<FavoritesEntity>()
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        val viewButton: Button
        val favoteButton: Button

        init {
            // Define click listener for the ViewHolder's View
            textView = view.findViewById(R.id.row_element_text_view)
            viewButton = view.findViewById(R.id.button)
            favoteButton = view.findViewById(R.id.favoriteButton)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.recipe_row_item, viewGroup, false)

        return ViewHolder(view)
    }

    fun refreshItem(viewHolder: ViewHolder,position: Int) {
        allFavorites = getAllFavorites()
        viewHolder.favoteButton.text =
            if(allFavorites.any { it.recipeId == dataSet[position].id })
                "Remove favorite"
            else
                "Add favorite"
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        //Log.i("RecipesAdapter", dataSet.size.toString())
        refreshItem(viewHolder,position)
        viewHolder.textView.text = dataSet[position].name
        viewHolder.viewButton.setOnClickListener {
            viewClicked(dataSet[position])
        }
        viewHolder.favoteButton.setOnClickListener{
            favoriteClicked(dataSet[position])
            viewHolder.favoteButton.text =
                if(viewHolder.favoteButton.text == "Add favorite")
                    "Remove favorite"
                else
                    "Add favorite"
        }
    }

    public fun updateData(data: List<RecipeModel>) {
        dataSet.clear()
        dataSet.addAll(data)
        notifyDataSetChanged()
    }

    public fun updateNewRecipeData(data:List<NewRecipeModel>){
        dataSet.clear()
        newDataSet.addAll(data)
        notifyDataSetChanged()
    }

    override fun getItemCount() = dataSet.size

}