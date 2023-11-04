package com.kristalcraft.pizzaapp.dishes_feature.domain.model

import com.kristalcraft.delegate_adapter.DelegateAdapterItem

data class CategoryModel (
    val id: Int,
    val name: String,
    val isSelected: Boolean
): DelegateAdapterItem {
    override fun id(): Any {
        return id
    }

    override fun content(): Any {
        return "$id$name$isSelected"
    }
}