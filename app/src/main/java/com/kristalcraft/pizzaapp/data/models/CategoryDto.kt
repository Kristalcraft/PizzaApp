package com.kristalcraft.pizzaapp.data.models

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.kristalcraft.pizzaapp.domain.model.CategoryModel

@Entity(
    indices = [
        Index(value = ["id"])
    ]
)
data class CategoryDto (
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val name: String
)

fun CategoryDto.toCategoryModel(): CategoryModel{
    return CategoryModel(
        id = id?:-1,
        name = name
    )
}