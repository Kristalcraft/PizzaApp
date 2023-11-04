package com.kristalcraft.pizzaapp.dishes_feature.di

import com.kristalcraft.pizzaapp.di.AppComponent
import com.kristalcraft.pizzaapp.di.Application
import com.kristalcraft.pizzaapp.dishes_feature.data.db.DishDao
import com.kristalcraft.pizzaapp.dishes_feature.data.remote.DishApi
import com.kristalcraft.pizzaapp.dishes_feature.data.repository.DishRepositoryImpl
import com.kristalcraft.pizzaapp.dishes_feature.domain.repository.DishRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class DishRepositoryModule {

    @Provides
    @DishesFeature
    fun provideRetrofit(appComponent: AppComponent): Retrofit {
        return appComponent.provideRetrofit()
    }

    @Provides
    @DishesFeature
    fun provideDao(appComponent: AppComponent): DishDao {
        return appComponent.provideDishDao()
    }

    @Provides
    @DishesFeature
    fun provideApi(retrofit: Retrofit): DishApi {
        return retrofit.create(DishApi::class.java)
    }

    @Provides
    @DishesFeature
    fun provideRepository(dishApi: DishApi, dishDao: DishDao): DishRepository{
        return DishRepositoryImpl(dishApi, dishDao)
    }
}