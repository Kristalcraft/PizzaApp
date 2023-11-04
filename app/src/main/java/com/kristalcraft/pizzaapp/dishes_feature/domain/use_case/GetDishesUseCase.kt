package com.kristalcraft.pizzaapp.dishes_feature.domain.use_case

import com.kristalcraft.pizzaapp.common.Resource
import com.kristalcraft.pizzaapp.dishes_feature.data.models.mapToDishModel
import com.kristalcraft.pizzaapp.dishes_feature.domain.model.DishModel
import com.kristalcraft.pizzaapp.dishes_feature.domain.repository.DishRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetDishesUseCase @Inject constructor(
    private val dishRepo: DishRepository
) {
    operator fun invoke(category: String): Flow<Resource<List<DishModel>>> = flow {
            dishRepo.getDishes(category)
                .onStart {
                   emit(Resource.Loading())
                }
                .catch {
                    if (it is HttpException){
                        emit(Resource.Error(it.localizedMessage?: "Unexpected Error"))
                    } else if (it is IOException) {
                        emit(Resource.Error("Check your internet connection"))
                    }
                }
                .collect{ list ->
                    emit(Resource.Success(data = list.map { it.mapToDishModel() }))
                }
    }

}