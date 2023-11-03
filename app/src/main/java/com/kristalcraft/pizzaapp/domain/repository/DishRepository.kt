package com.kristalcraft.pizzaapp.domain.repository

import com.kristalcraft.pizzaapp.data.models.CategoryDto
import com.kristalcraft.pizzaapp.data.models.DishDto
import kotlinx.coroutines.flow.Flow

interface DishRepository {

    suspend fun getDishes(category: String): Flow<List<DishDto>>
    suspend fun getCategories(): Flow<List<CategoryDto>>
}