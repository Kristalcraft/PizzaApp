package com.kristalcraft.pizzaapp.di

import android.content.Context
import com.kristalcraft.pizzaapp.App
import dagger.BindsInstance
import dagger.Component
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

}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class Application