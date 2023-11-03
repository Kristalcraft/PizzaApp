package com.kristalcraft.pizzaapp.data.models

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    indices = [
        Index(value = ["id"])
    ]
)
data class CategoryModel (
    @PrimaryKey
    val id: Int,
    val name: String
)