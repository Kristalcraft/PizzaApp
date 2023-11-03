package com.kristalcraft.pizzaapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kristalcraft.pizzaapp.data.db.DishDB
import com.kristalcraft.pizzaapp.data.db.DishDao
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executors

@Module
class DatabaseModule {

    @Provides
    fun provideDatabase(applicationContext: Context): DishDB {
        val callback = object: RoomDatabase.QueryCallback {
            override fun onQuery(sqlQuery: String, bindArgs: List<Any?>) {
                println("SQL Query: $sqlQuery SQL Args: $bindArgs")
            }
        }
        return Room.databaseBuilder(applicationContext, DishDB::class.java, DATABASE_NAME)
            .setQueryCallback( callback , Executors.newSingleThreadExecutor())
            .build()
    }

    @Provides
    fun provideDishDao(dishDB: DishDB): DishDao {
        return dishDB.getDishDao()
    }



    companion object{
        const val DATABASE_NAME = "DISHES_DB"
    }
}