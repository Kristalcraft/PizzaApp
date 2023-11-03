package com.kristalcraft.pizzaapp.dishes_feature.di

import com.kristalcraft.pizzaapp.di.AppComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Scope

@Component(modules = [DishRepositoryModule::class, DishViewModelModule::class])
@DishesFeature
interface DishesComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun appComponent(appComponent: AppComponent): Builder
        fun build(): DishesComponent
    }
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class DishesFeature