package com.tasty.recipesapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.tasty.recipesapp.R
import com.tasty.recipesapp.data.entities.RecipeEntity
import com.tasty.recipesapp.data.models.NewRecipeModel
import com.tasty.recipesapp.data.models.RecipeModel
import com.tasty.recipesapp.ui.profile.ProfileViewModel

class NewRecipesAdapter(val deleteClick: (RecipeEntity) -> Unit,val viewClicked: (Long) -> Unit): RecyclerView.Adapter<NewRecipesAdapter.ViewHolder>() {
    private val gson = Gson()
    private val dataSet = mutableListOf<NewRecipeModel>()
    public var context: Context? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        val deleteButton: Button
        val viewButton: Button
        val bgImage: ImageView

        init {
            // Define click listener for the ViewHolder's View
            textView = view.findViewById(R.id.row_element_text_view)
            deleteButton = view.findViewById(R.id.delete_button)
            viewButton = view.findViewById(R.id.view_button)
            bgImage = view.findViewById(R.id.bgImage)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.private_recipe_row_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        //Log.i("RecipesAdapter", dataSet.size.toString())
        viewHolder.textView.text = dataSet[position].title
        context?.let {
            Glide.with(it)
                .load(dataSet[position].pictureURL)
                .fitCenter()
                .placeholder(R.drawable.ic_launcher_foreground)
                .fallback(R.drawable.ic_launcher_foreground)
                .into(viewHolder.bgImage)
        }
        viewHolder.deleteButton.setOnClickListener {
            val recipeEntity = RecipeEntity(
                internalId = dataSet[position].id,
                json = gson.toJson(dataSet[position])
            )
            deleteClick(recipeEntity)
            dataSet.removeAt(position)
            notifyDataSetChanged()
        }
        viewHolder.viewButton.setOnClickListener {
            viewClicked(dataSet[position].id)
        }
    }

    public fun updateData(data: List<NewRecipeModel>) {
        dataSet.clear()
        dataSet.addAll(data)
        notifyDataSetChanged()
    }

    override fun getItemCount() = dataSet.size

}