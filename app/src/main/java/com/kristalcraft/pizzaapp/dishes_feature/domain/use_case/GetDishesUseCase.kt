package com.kristalcraft.pizzaapp.dishes_feature.domain.use_case

import com.kristalcraft.pizzaapp.common.Resource
import com.kristalcraft.pizzaapp.dishes_feature.data.models.mapToDishModel
import com.kristalcraft.pizzaapp.dishes_feature.domain.model.DishModel
import com.kristalcraft.pizzaapp.dishes_feature.domain.repository.DishRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetDishesUseCase @Inject constructor(
    private val dishRepo: DishRepository
) {
    operator fun invoke(category: String): Flow<Resource<List<DishModel>>> = flow {
            emit(Resource.Loading())
        try {
            val data = dishRepo.getDishes(category)
            emit(Resource.Success(data = data.map { it.mapToDishModel() }))
        } catch (e: Exception) {
            when (e) {
                is HttpException -> {
                    emit(Resource.Error(e.localizedMessage?: "Unexpected Error"))
                }
                is IOException -> {
                    emit(Resource.Error("Check your internet connection"))
                }
                else -> {
                    emit(Resource.Error("Unexpected error"))
                }
            }
        }

    }

}