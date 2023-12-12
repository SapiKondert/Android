package com.tasty.recipesapp.data.models

import android.os.Parcel
import android.os.Parcelable

data class NewRecipeModel(
    val id:Long,
    val title:String,
    val description:String,
    val pictureURL:String?,
    val instructions:List<String>?,
    val ingredients:List<String>?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString()?: "",
        parcel.createStringArrayList(),
        parcel.createStringArrayList()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(pictureURL)
        parcel.writeStringList(instructions)
        parcel.writeStringList(ingredients)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<NewRecipeModel> = object : Parcelable.Creator<NewRecipeModel> {
            override fun createFromParcel(parcel: Parcel): NewRecipeModel {
                return NewRecipeModel(parcel)
            }

            override fun newArray(size: Int): Array<NewRecipeModel?> {
                return arrayOfNulls(size)
            }
        }
    }
}
