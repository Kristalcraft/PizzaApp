package com.kristalcraft.pizzaapp.dishes_feature.ui.recyclerview

import android.graphics.Rect
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kristalcraft.delegate_adapter.DelegateAdapter
import com.kristalcraft.delegate_adapter.DelegateAdapterItem
import com.kristalcraft.pizzaapp.databinding.DishViewholderBinding
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

            val textView = binding.subtitle
            minimizeTextViewWhenCut(textView)
        }
    }

    private fun minimizeTextViewWhenCut(textView: TextView){
        val observer = textView?.viewTreeObserver
        observer?.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                textView?.let { view ->
                    val lineHeight: Int
                    lineHeight = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        val bounds = Rect()
                        textView.getLineBounds(0, bounds)
                        bounds.bottom - bounds.top
                    } else {
                        textView.lineHeight
                    }
                    val maxLines = textView.height / lineHeight
                    textView.maxLines = maxLines
                    textView.viewTreeObserver.removeOnGlobalLayoutListener(this)
                }
            }
        })
    }
}