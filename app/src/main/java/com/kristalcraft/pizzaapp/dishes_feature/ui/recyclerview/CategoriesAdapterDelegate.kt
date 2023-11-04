package com.kristalcraft.pizzaapp.dishes_feature.ui.recyclerview

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
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
            } else {
                binding.name.typeface= Typeface.create(null,400,false)
                binding.name.setTextColor(binding.name.context.resources.getColor(R.color.grey_text))
                binding.background.setCardBackgroundColor(binding.name.context.resources.getColor(R.color.white))
            }
        }
    }
}