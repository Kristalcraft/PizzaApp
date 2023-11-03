package com.kristalcraft.pizzaapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kristalcraft.pizzaapp.data.models.CategoryDto
import com.kristalcraft.pizzaapp.data.models.DishDto

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