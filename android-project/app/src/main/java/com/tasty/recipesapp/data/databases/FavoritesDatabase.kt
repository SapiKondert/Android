package com.tasty.recipesapp.data.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tasty.recipesapp.data.daos.FavoritesDao
import com.tasty.recipesapp.data.entities.FavoritesEntity

@Database(entities = [FavoritesEntity::class], version = 1, exportSchema =
false)
abstract class FavoritesDatabase : RoomDatabase() {
    abstract fun favoritesDao(): FavoritesDao
    companion object {
        // Create a singleton instance of the database
        @Volatile
        private var INSTANCE: FavoritesDatabase? = null
        fun getDatabase(context: Context): FavoritesDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavoritesDatabase::class.java,
                    "favorite_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}