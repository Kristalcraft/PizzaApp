package com.kristalcraft.pizzaapp.data.repository

import com.kristalcraft.pizzaapp.data.db.DishDao
import com.kristalcraft.pizzaapp.data.models.CategoryDto
import com.kristalcraft.pizzaapp.data.models.DishDto
import com.kristalcraft.pizzaapp.data.remote.DishApi
import com.kristalcraft.pizzaapp.domain.repository.DishRepository
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class DishRepositoryImpl @Inject constructor(
    private val dishApi: DishApi,
    private val dishDao: DishDao
): DishRepository {
    override suspend fun getDishes(category: String): Flow<List<DishDto>> {
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
        val dishes = dishApi.getDishes()
        val categories = dishes.map {
           CategoryDto(name = it.category, id = null)
        }
        putCategoriesIntoDb(categories)
        dishDao.insert(dishes)
    }

    private suspend fun putCategoriesIntoDb(categories: List<CategoryDto>){
        dishDao.insert(categories)
    }
}