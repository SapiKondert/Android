package com.tasty.recipesapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tasty.recipesapp.R
import com.tasty.recipesapp.data.models.RecipeModel

class RecipesAdapter(private val dataSet: MutableList<RecipeModel>): RecyclerView.Adapter<RecipesAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView

        init {
            // Define click listener for the ViewHolder's View
            textView = view.findViewById(R.id.row_element_text_view)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.recipe_row_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        //Log.i("RecipesAdapter", dataSet.size.toString())
        if (dataSet.size > position)
            viewHolder.textView.text = dataSet[position].name
    }

    public fun updateData(data: List<RecipeModel>) {
        dataSet.clear()
        dataSet.addAll(data)
    }

    override fun getItemCount() = 100

}