package com.kristalcraft.pizzaapp

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.kristalcraft.pizzaapp.di.AppComponent
import com.kristalcraft.pizzaapp.di.DaggerAppComponent

class App: Application() {


    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)

        appComponent = DaggerAppComponent
            .builder()
            .applicationContext(applicationContext)
            .build()

    }


}