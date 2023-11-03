package com.kristalcraft.pizzaapp.dishes_feature.domain.repository

import com.kristalcraft.pizzaapp.dishes_feature.data.models.CategoryDto
import com.kristalcraft.pizzaapp.dishes_feature.data.models.DishDto
import kotlinx.coroutines.flow.Flow

interface DishRepository {

    suspend fun getDishes(category: String): Flow<List<DishDto>>
    suspend fun getCategories(): Flow<List<CategoryDto>>
}