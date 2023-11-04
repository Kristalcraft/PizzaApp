package com.kristalcraft.pizzaapp.dishes_feature.ui.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kristalcraft.delegate_adapter.DelegateAdapter
import com.kristalcraft.delegate_adapter.DelegateAdapterItem
import com.kristalcraft.pizzaapp.databinding.CategoryViewholderBinding
import com.kristalcraft.pizzaapp.databinding.DishViewholderBinding
import com.kristalcraft.pizzaapp.dishes_feature.domain.model.CategoryModel
import com.kristalcraft.pizzaapp.dishes_feature.domain.model.DishModel

class DishAdapterDelegate() : DelegateAdapter<DishModel, DishAdapterDelegate.DishViewHolder>(DishModel::class.java) {

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = DishViewholderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DishViewHolder(binding)
    }

    override fun bindViewHolder(
        model: DishModel,
        viewHolder: DishViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>
    ) {
        viewHolder.bind(model)
    }

    inner class DishViewHolder(private val binding: DishViewholderBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DishModel) {
            binding.image.setImageURI(item.imageUrl)
            binding.title.text = item.name
            binding.subtitle.text = item.description
            binding.price.text = "от ${item.price} руб"
        }
    }
}