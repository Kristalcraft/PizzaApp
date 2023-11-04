package com.kristalcraft.pizzaapp.dishes_feature.di

import androidx.fragment.app.Fragment
import com.kristalcraft.pizzaapp.di.AppComponent
import com.kristalcraft.pizzaapp.dishes_feature.ui.MenuFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Scope

@Component(modules = [DishRepositoryModule::class, DishViewModelModule::class])
@DishesFeature
interface DishesComponent {

    fun inject(menuFragment: MenuFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun appComponent(appComponent: AppComponent): Builder
        @BindsInstance
        fun fragment(fragment: Fragment): Builder
        fun build(): DishesComponent
    }
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class DishesFeature