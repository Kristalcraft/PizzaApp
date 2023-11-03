package com.kristalcraft.pizzaapp.data.models

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.kristalcraft.delegate_adapter.DelegateAdapterItem

@Entity(
    indices = [
        Index(value = ["id"])
    ]
)
data class DishModel (
    @Expose
    @PrimaryKey
    val id: Int,
    @Expose
    val name: String,
    @Expose
    val description: String,
    @Expose
    val imageUrl: String,
    @Expose
    val price: Int,
    @Expose
    val categoryId: Int
): DelegateAdapterItem{
    override fun id(): Any {
        return id
    }

    override fun content(): Any {
        return "$id$name$price"
    }

}