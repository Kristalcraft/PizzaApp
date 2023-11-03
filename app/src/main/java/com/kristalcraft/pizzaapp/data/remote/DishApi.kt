package com.kristalcraft.pizzaapp.data.remote

import com.kristalcraft.pizzaapp.data.models.DishDto
import retrofit2.http.GET

interface DishApi {
    @GET("/dishes")
    suspend fun getDishes(): List<DishDto>
}