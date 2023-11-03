package com.kristalcraft.pizzaapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kristalcraft.pizzaapp.data.models.DishModel

@Database(
    entities = [
        DishModel::class
    ],
    version = 1
)
abstract class DishDB: RoomDatabase() {

    abstract fun getDishDao(): DishDao
}