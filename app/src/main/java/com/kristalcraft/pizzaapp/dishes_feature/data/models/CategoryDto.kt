package com.kristalcraft.pizzaapp.dishes_feature.data.models

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.kristalcraft.pizzaapp.dishes_feature.domain.model.CategoryModel

@Entity(
    indices = [
        Index(value = ["name"], unique = true)
    ]
)
data class CategoryDto (
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val name: String
)

fun CategoryDto.mapToCategoryModel(): CategoryModel {
    return CategoryModel(
        id = id?:-1,
        name = name,
        isSelected = false
    )
}