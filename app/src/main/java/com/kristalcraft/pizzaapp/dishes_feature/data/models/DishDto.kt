package com.kristalcraft.pizzaapp.dishes_feature.data.models

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.kristalcraft.pizzaapp.dishes_feature.domain.model.DishModel

@Entity(
    indices = [
        Index(value = ["id"])
    ]
)
data class DishDto (
    @Expose
    @PrimaryKey
    val id: Int,
    @Expose
    val name: String,
    @Expose
    @SerializedName("desc")
    val description: String,
    @Expose
    @SerializedName("image_url")
    val imageUrl: String,
    @Expose
    val price: Int,
    @Expose
    val category: String
)

fun DishDto.mapToDishModel(): DishModel {
    return DishModel(
        id = id,
        name = name,
        description = description,
        imageUrl = imageUrl,
        price = price
    )
}