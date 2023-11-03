package com.kristalcraft.pizzaapp.dishes_feature.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kristalcraft.pizzaapp.dishes_feature.data.models.CategoryDto
import com.kristalcraft.pizzaapp.dishes_feature.data.models.DishDto

@Database(
    entities = [
        DishDto::class,
        CategoryDto::class
    ],
    version = 1
)
abstract class DishDB: RoomDatabase() {
    abstract fun getDishDao(): DishDao
}