package com.tasty.recipesapp.ui.recipe

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.tasty.recipesapp.data.dtos.InstructionDTO
import java.io.IOException
import java.io.InputStream


class RecipeListViewModel : ViewModel() {
    fun fetchInstructionData(){
        val jsonString = """{
                "appliance": null,
                "end_time": 26500,
                "temperature": null,
                "id": 43381,
                "position": 1,
                "display_text": "In a blender or food processor, combine the yogurt, lime
                juice, pepper, and chili powder and pulse to combine. Add ½ of the avocado and
                blend until creamy.",
                "start_time": 7000
                }"""
        val gson = Gson()
        val instructionDTO = gson.fromJson(jsonString,InstructionDTO::class.java)
        Log.i("InstructionDTO",instructionDTO.display_text)
    }

    fun fetchInstructionsData(){
        val jsonArray = """[
                        {
                          "appliance": null,
                          "end_time": 26500,
                          "temperature": null,
                          "id": 43381,
                          "position": 1,
                          "display_text": "In a blender or food processor, combine the yogurt, lime juice, pepper, and chili powder and pulse to combine. Add ½ of the avocado and blend until creamy.",
                          "start_time": 7000
                        },
                        {
                          "start_time": 29000,
                          "appliance": null,
                          "end_time": 43500,
                          "temperature": null,
                          "id": 43382,
                          "position": 2,
                          "display_text": "In a medium bowl, combine the chicken, yogurt sauce, celery, the remaining ½ avocado, onion, and salt. Mix until well combined."
                        },
                        {
                          "id": 43383,
                          "position": 3,
                          "display_text": "Serve on low-carb bread and garnish with cilantro, or as desired.",
                          "start_time": 0,
                          "appliance": null,
                          "end_time": 0,
                          "temperature": null
                        },
                        {
                          "appliance": null,
                          "end_time": 47166,
                          "temperature": null,
                          "id": 43384,
                          "position": 4,
                          "display_text": "Enjoy!",
                          "start_time": 44666
                        }
                    ]"""
        val gson = Gson()
        val instructionDTO = gson.fromJson(jsonArray,Array<InstructionDTO>::class.java)
        for (item in instructionDTO){
            Log.i("InstructionDTO",item.display_text)
        }
    }

    fun readInstructionsFromFile(requireContext: Context) {
        val json = loadJSONFromAsset(requireContext)
        Log.i("JSON",json.toString())
    }

    fun loadJSONFromAsset(requireContext: Context): String? {
        var json: String? = null
        json = try {
            val `is`: InputStream = requireContext.assets.open("instruction_all.json")
            val size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            String(buffer, charset("UTF-8"))
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }
}
