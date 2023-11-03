package com.kristalcraft.pizzaapp.domain.model

import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.kristalcraft.delegate_adapter.DelegateAdapterItem

data class DishModel (
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String,
    val price: Int
): DelegateAdapterItem {
    override fun id(): Any {
        return id
    }

    override fun content(): Any {
        return "$id$name$price"
    }
}