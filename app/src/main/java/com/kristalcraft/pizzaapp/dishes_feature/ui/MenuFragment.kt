package com.kristalcraft.pizzaapp.dishes_feature.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kristalcraft.delegate_adapter.DelegateAdapterItem
import com.kristalcraft.delegate_adapter.MainCompositeAdapter
import com.kristalcraft.pizzaapp.App
import com.kristalcraft.pizzaapp.databinding.FragmentMenuBinding
import com.kristalcraft.pizzaapp.dishes_feature.di.DaggerDishesComponent
import com.kristalcraft.pizzaapp.dishes_feature.di.DishesComponent
import com.kristalcraft.pizzaapp.dishes_feature.domain.model.CategoryModel
import com.kristalcraft.pizzaapp.dishes_feature.ui.recyclerview.CategoriesAdapterDelegate
import com.kristalcraft.pizzaapp.dishes_feature.ui.recyclerview.DishAdapterDelegate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject


class MenuFragment : Fragment() {

    private lateinit var categoriesComponent: DishesComponent
    private lateinit var binding: FragmentMenuBinding
    private val fragmentCoroutineScope = CoroutineScope(Job() + Dispatchers.Main)

    @Inject
    lateinit var viewModel: DishesViewModel

    private val categoryAdapter by lazy {
        MainCompositeAdapter.Builder()
            .add(CategoriesAdapterDelegate(categoryListener))
            .build()
    }

    private val dishAdapter by lazy {
        MainCompositeAdapter.Builder()
            .add(DishAdapterDelegate())
            .build()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        buildComponent()
        binding = FragmentMenuBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.categoriesRecycler.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL , false )
        binding.categoriesRecycler.adapter = categoryAdapter

        binding.dishesRecycler.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL , false )
        binding.dishesRecycler.adapter = dishAdapter

        fragmentCoroutineScope.launch {
            viewModel.categoriesState.collect{
                val list = ArrayList<CategoryModel>()
                it.forEach {thisCat->
                    list.add(
                        CategoryModel(
                            thisCat.id,
                            thisCat.name,
                            thisCat.isSelected
                        )
                    )
                }
                categoryAdapter.submitList(list as List<DelegateAdapterItem>?)
            }
        }

        fragmentCoroutineScope.launch {
            viewModel.dishesState.collect{
                dishAdapter.submitList(it)
            }
        }

        fragmentCoroutineScope.launch {
            viewModel.messageState.collect{
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        }

        fragmentCoroutineScope.launch {
            viewModel.loadingState.collect{ loading ->
                binding.swipeRefresh.isRefreshing = loading
                binding.swipeRefresh.setOnRefreshListener {
                    viewModel.categoriesState.value.firstOrNull { it.isSelected }
                        ?.let { it1 -> viewModel.getDishes(it1.name) }
                }
            }
        }
    }

    private fun buildComponent() {
        val appComponent = (context?.applicationContext as App).appComponent
        categoriesComponent = DaggerDishesComponent
            .builder()
            .appComponent(appComponent)
            .fragment(this)
            .build()
        categoriesComponent.inject(this)
    }

    private val categoryListener = { category: String ->
        viewModel.getDishes(category)
    }

    override fun onDestroy() {
        fragmentCoroutineScope.cancel()
        super.onDestroy()
    }

}