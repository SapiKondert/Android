package com.tasty.recipesapp.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "favorite")
data class FavoritesEntity(
    @PrimaryKey(autoGenerate = true)
    val internalId: Long = 0L, // Room will handle generating this ID
    @ColumnInfo(name = "recipeId")
    @SerializedName("recipe_id")
    val recipeId: Int
)