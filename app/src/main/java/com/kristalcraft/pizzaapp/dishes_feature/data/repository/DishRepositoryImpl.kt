package com.kristalcraft.pizzaapp.dishes_feature.data.repository

import android.util.Log
import com.kristalcraft.pizzaapp.dishes_feature.data.db.DishDao
import com.kristalcraft.pizzaapp.dishes_feature.data.models.CategoryDto
import com.kristalcraft.pizzaapp.dishes_feature.data.models.DishDto
import com.kristalcraft.pizzaapp.dishes_feature.data.remote.DishApi
import com.kristalcraft.pizzaapp.dishes_feature.domain.repository.DishRepository
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class DishRepositoryImpl @Inject constructor(
    private val dishApi: DishApi,
    private val dishDao: DishDao
): DishRepository {
    override suspend fun getDishes(category: String): List<DishDto> {
        coroutineScope {
            launch {
                getDishesFromApi()
            }
        }
        return dishDao.getDishesByCategory(category)
    }

    override suspend fun getCategories(): Flow<List<CategoryDto>> {
        coroutineScope {
            launch {
                getDishesFromApi()
            }
        }
        return dishDao.getCategories()
    }

    private suspend fun getDishesFromApi(){
        var dishes: List<DishDto> = emptyList()
        try {
            dishes = dishApi.getDishes()
        } catch (e: Exception){
            Log.e("Exception", "${e.message} ${e.cause}")
        }
        val categories = dishes.map {
           CategoryDto(name = it.category, id = null)
        }
        putCategoriesIntoDb(categories.distinct())
        dishDao.insertDishes(dishes)
    }

    private suspend fun putCategoriesIntoDb(categories: List<CategoryDto>){
        dishDao.insertCategories(categories)
    }
}