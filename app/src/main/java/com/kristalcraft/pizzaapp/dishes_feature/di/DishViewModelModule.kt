package com.kristalcraft.pizzaapp.dishes_feature.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides

@Module
class DishViewModelModule {

    /*@Provides
    @DishesFeature
    fun provideViewModel(viewModelFactory: ViewModelFactory, fragmentContext: Fragment): CategoriesViewModel {
        return ViewModelProvider(fragmentContext, viewModelFactory)[CategoriesViewModel::class.java]
    }

    @Provides
    @DishesFeature
    fun provideFactory(categoriesApiHelper: CategoriesApiHelper): ViewModelFactory {
        return ViewModelFactory(categoriesApiHelper)
    }*/

}