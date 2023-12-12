package com.tasty.recipesapp.utils

import android.util.Log
import com.tasty.recipesapp.data.dtos.*
import com.tasty.recipesapp.data.models.*
import com.tasty.recipesapp.utils.Mapping.toRecipeModel

object Mapping {
    fun InstructionDTO.toInstructionModel(): InstructionModel {
        return InstructionModel(
            id = this.id,
            displayText = this.displayText,
            time = InstructionTime(this.startTime, this.endTime)
        )
    }

    fun NutritionDTO.toNutritionModel(): NutritionModel {
        return NutritionModel(
            fiber = this.fiber,
            protein = this.protein,
            fat = this.fat,
            calories = this.calories,
            sugar = this.sugar,
            carbohydrates = this.carbohydrates
        )
    }

    fun RecipeDTO.toRecipeModel(): RecipeModel {
        return RecipeModel(
            id = this.id,
            name = this.name,
            thumbnailURL = this.thumbnailURL,
            instructions = this.instructions.map{ it.toInstructionModel() },
            nutrition = this.nutrition.toNutritionModel(),
            credits = this.credits.map { it.toCreditModel() },
            sections = this.sections.map { it.toSectionModel() },
            tags = this.tags.map { it.toTagModel() },
            topic = this.topics.map { it.toTopicModel() },
            userRating = this.userRating.toUserRatingModel()

        )
    }

    fun NewRecipeDTO.toModel():NewRecipeModel{
        Log.i("Mapping", "Id: ${this.id}")
        Log.i("Mapping", "Title: ${this.title}")
        Log.i("Mapping", "Picture URL: ${this.pictureURL}")
        Log.i("Mapping", "Description: ${this.description}")
        Log.i("Mapping", "Ingredients: ${this.ingredients}")
        Log.i("Mapping", "Instructions: ${this.instructions}")
        return NewRecipeModel(
            id = this.id,
            title = this.title,
            description = this.description,
            pictureURL = this.pictureURL,
            ingredients = this.ingredients,
            instructions = this.instructions
        )
    }

    fun UserRatingDTO.toUserRatingModel(): UserRatingModel {
        return UserRatingModel(
            countPositive = this.countPositive,
            score = this.score,
            countNegative = this.countNegative
        )
    }

    fun TopicDTO.toTopicModel(): TopicModel {
        return TopicModel(
            name = this.name,
            slug = this.slug
        )
    }

    fun TagDTO.toTagModel(): TagModel {
        return TagModel(
            displayName = this.displayName,
            type = this.type,
            rootTagType = this.rootTagType,
            name = this.name,
            id = this.id
        )
    }

    fun SectionDTO.toSectionModel(): SectionModel {
        return SectionModel(
            components = this.components.map { it.toComponentModel() },
            name = this.name,
            position = this.position
        )
    }

    fun ComponentDTO.toComponentModel(): ComponentModel {
        return ComponentModel(
            extraComment = this.extraComment,
            ingredient = this.ingredient.toIngredientModel(),
            id = this.id,
            position = this.position,
            measurements = this.measurements.map { it.toMeasurementModel() },
            rawText = this.rawText
        )
    }

    fun IngredientDTO.toIngredientModel(): IngredientModel {
        return IngredientModel(
            name = this.name,
            id = this.id,
            displayPlural = this.displayPlural,
            displaySingular = this.displaySingular,
            updatedAt = this.updatedAt,
            createdAt = this.createdAt
        )
    }

    fun MeasurementDTO.toMeasurementModel(): MeasurementModel {
        return MeasurementModel(
            unit = this.unit.toUnitModel(),
            quantity = this.quantity,
            id = this.id
        )
    }

    fun UnitDTO.toUnitModel(): UnitModel {
        return UnitModel(
            system = this.system,
            name = this.name,
            displayPlural = this.displayPlural,
            displaySingular = this.displaySingular,
            abbreviation = this.abbreviation
        )
    }

    fun CreditDTO.toCreditModel(): CreditModel {
        return CreditModel(
            name = this.name,
            type = this.type
        )
    }

    fun List<RecipeDTO>.toRecipeModelList(): List<RecipeModel> {
        return this.map { it.toRecipeModel() }
    }

}