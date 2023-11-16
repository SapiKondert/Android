package com.tasty.recipesapp.data.models

import com.tasty.recipesapp.data.dtos.ComponentDTO

data class SectionModel(
    val components: List<ComponentModel>,
    val name: String?,
    val position: Int
)
