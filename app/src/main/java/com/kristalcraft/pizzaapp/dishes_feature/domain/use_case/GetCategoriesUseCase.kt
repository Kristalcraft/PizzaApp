package com.kristalcraft.pizzaapp.dishes_feature.domain.use_case

import com.kristalcraft.pizzaapp.common.Resource
import com.kristalcraft.pizzaapp.dishes_feature.data.models.mapToCategoryModel
import com.kristalcraft.pizzaapp.dishes_feature.domain.model.CategoryModel
import com.kristalcraft.pizzaapp.dishes_feature.domain.repository.DishRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val dishRepo: DishRepository
) {
    operator fun invoke(): Flow<Resource<List<CategoryModel>>> = flow {
        dishRepo.getCategories()
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
                emit(Resource.Success(data = list.map { it.mapToCategoryModel() }))
            }
    }
}