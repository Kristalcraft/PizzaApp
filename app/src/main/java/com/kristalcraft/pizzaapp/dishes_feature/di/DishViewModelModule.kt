package com.kristalcraft.pizzaapp.dishes_feature.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kristalcraft.pizzaapp.dishes_feature.domain.use_case.GetCategoriesUseCase
import com.kristalcraft.pizzaapp.dishes_feature.domain.use_case.GetDishesUseCase
import com.kristalcraft.pizzaapp.dishes_feature.ui.DishesViewModel
import com.kristalcraft.pizzaapp.dishes_feature.ui.ViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class DishViewModelModule {

    @Provides
    @DishesFeature
    fun provideViewModel(viewModelFactory: ViewModelFactory, fragmentContext: Fragment): DishesViewModel {
        return ViewModelProvider(fragmentContext, viewModelFactory)[DishesViewModel::class.java]
    }

    @Provides
    @DishesFeature
    fun provideFactory(getDishesUseCase: GetDishesUseCase,
                       getCategoriesUseCase: GetCategoriesUseCase
    ): ViewModelFactory {
        return ViewModelFactory(getCategoriesUseCase, getDishesUseCase)
    }

}