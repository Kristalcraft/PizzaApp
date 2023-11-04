package com.kristalcraft.pizzaapp.dishes_feature.ui.recyclerview

import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.alpha
import androidx.recyclerview.widget.RecyclerView
import com.kristalcraft.delegate_adapter.DelegateAdapter
import com.kristalcraft.delegate_adapter.DelegateAdapterItem
import com.kristalcraft.pizzaapp.R
import com.kristalcraft.pizzaapp.databinding.CategoryViewholderBinding
import com.kristalcraft.pizzaapp.dishes_feature.domain.model.CategoryModel

class CategoriesAdapterDelegate(val onCategoryClickListener: (name: String) -> Unit) : DelegateAdapter<CategoryModel, CategoriesAdapterDelegate.CategoryViewHolder>(CategoryModel::class.java) {

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = CategoryViewholderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun bindViewHolder(
        model: CategoryModel,
        viewHolder: CategoryViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>
    ) {
        viewHolder.bind(model)
    }

    inner class CategoryViewHolder(private val binding: CategoryViewholderBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CategoryModel) {
            binding.name.text = item.name
            binding.root.setOnClickListener{onCategoryClickListener.invoke(item.name)}
            if (item.isSelected){
                binding.name.typeface= Typeface.create(null,600,false)
                binding.name.setTextColor(binding.name.context.resources.getColor(R.color.red))
                binding.background.setCardBackgroundColor(binding.name.context.resources.getColor(R.color.red_transparent))
                binding.background.outlineSpotShadowColor = Color.TRANSPARENT
                binding.background.outlineAmbientShadowColor = Color.TRANSPARENT
            } else {
                binding.name.typeface= Typeface.create(null,400,false)
                binding.name.setTextColor(binding.name.context.resources.getColor(R.color.grey_text))
                binding.background.setCardBackgroundColor(binding.name.context.resources.getColor(R.color.white))
                binding.background.outlineSpotShadowColor = binding.name.context.resources.getColor(R.color.grey_shadow)
                binding.background.outlineAmbientShadowColor = binding.name.context.resources.getColor(R.color.grey_shadow)
            }
        }
    }
}