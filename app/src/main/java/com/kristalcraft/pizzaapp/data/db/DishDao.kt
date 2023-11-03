package com.kristalcraft.pizzaapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kristalcraft.pizzaapp.data.models.CategoryDto
import com.kristalcraft.pizzaapp.data.models.DishDto
import kotlinx.coroutines.flow.Flow

@Dao
interface DishDao {


    @Query("SELECT * FROM DishDto WHERE category = :category ORDER by id")
    suspend fun getDishesByCategory(category: String): Flow<List<DishDto>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entities: List<DishDto>)

    @Query("SELECT * FROM CategoryDto ORDER by id")
    suspend fun getCategories(): Flow<List<CategoryDto>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(categories: List<CategoryDto>)

}