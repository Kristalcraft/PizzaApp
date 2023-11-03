package com.kristalcraft.pizzaapp.dishes_feature.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kristalcraft.pizzaapp.dishes_feature.data.models.CategoryDto
import com.kristalcraft.pizzaapp.dishes_feature.data.models.DishDto
import kotlinx.coroutines.flow.Flow

@Dao
interface DishDao {


    @Query("SELECT * FROM DishDto WHERE category = :category ORDER by id")
    fun getDishesByCategory(category: String): Flow<List<DishDto>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDishes(dishes: List<DishDto>)

    @Query("SELECT * FROM CategoryDto ORDER by id")
    fun getCategories(): Flow<List<CategoryDto>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCategories(categories: List<CategoryDto>)

}