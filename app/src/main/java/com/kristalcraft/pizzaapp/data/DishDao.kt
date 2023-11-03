package com.kristalcraft.pizzaapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.kristalcraft.pizzaapp.data.models.DishModel
import kotlinx.coroutines.flow.Flow

@Dao
interface DishDao {

    @Query("SELECT * FROM DishModel WHERE categoryId = :categoryId ORDER by id")
    suspend fun getDishCart(categoryId: Int): Flow<List<DishModel>>?

    /*@Query("SELECT * FROM DishModel WHERE id = :id")
    suspend fun getDishCart(id: Int): DishModel?*/

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(entity: DishModel)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entities: List<DishModel>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entity: DishModel)

}