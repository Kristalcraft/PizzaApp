package com.kristalcraft.pizzaapp.di

import android.content.Context
import com.kristalcraft.pizzaapp.App
import com.kristalcraft.pizzaapp.dishes_feature.data.db.DishDao
import dagger.BindsInstance
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Scope

@Application
@Component(modules = [NetworkModule::class, DatabaseModule::class])
interface AppComponent{

    fun inject(app: App)


    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applicationContext(applicationContext: Context): Builder
        fun build(): AppComponent
    }

    fun provideRetrofit(): Retrofit
    fun provideDishDao(): DishDao

}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class Application