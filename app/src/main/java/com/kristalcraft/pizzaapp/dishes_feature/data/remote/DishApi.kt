package com.kristalcraft.pizzaapp.dishes_feature.data.remote

import com.kristalcraft.pizzaapp.dishes_feature.data.models.DishDto
import retrofit2.http.GET

interface DishApi {
    @GET("/dishes")
    suspend fun getDishes(): List<DishDto>
}