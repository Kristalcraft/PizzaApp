package com.kristalcraft.pizzaapp.dishes_feature.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.kristalcraft.pizzaapp.common.Resource
import com.kristalcraft.pizzaapp.dishes_feature.domain.model.CategoryModel
import com.kristalcraft.pizzaapp.dishes_feature.domain.model.DishModel
import com.kristalcraft.pizzaapp.dishes_feature.domain.use_case.GetCategoriesUseCase
import com.kristalcraft.pizzaapp.dishes_feature.domain.use_case.GetDishesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import javax.inject.Inject
import javax.inject.Singleton

class DishesViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getDishesUseCase: GetDishesUseCase
): ViewModel(){

    private var initCategory: Boolean = false

    private val _categoriesState: MutableStateFlow<List<CategoryModel>> = MutableStateFlow(listOf())
    val categoriesState: StateFlow<List<CategoryModel>> = _categoriesState

    private val _dishesState: MutableStateFlow<List<DishModel>> = MutableStateFlow(listOf())
    val dishesState: StateFlow<List<DishModel>> = _dishesState

    private val _messageState: MutableSharedFlow<String> = MutableSharedFlow()
    val messageState: SharedFlow<String> = _messageState

    private val _loadingState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loadingState: StateFlow<Boolean> = _loadingState

    init{
        getCategories()
    }

    fun getCategories(){
        getCategoriesUseCase().onEach {result ->
            when(result){
                is Resource.Success -> {
                    _categoriesState.update {
                        result.data?: emptyList()
                    }
                    if (!initCategory){
                        categoriesState.value.firstOrNull()?.let{
                            getDishes(it.name)
                        }
                        initCategory = true
                    }
                    _loadingState.value = false
                }
                is Resource.Loading -> {
                    _loadingState.value = true
                }
                is Resource.Error -> {
                    _loadingState.value = false
                    _messageState.emit(result.message?: "Something went wrong")
                }
            }
        }.launchIn(viewModelScope + Dispatchers.IO)
    }

    fun getDishes(category: String){
        selectTag(category)
        getDishesUseCase(category).onEach {result ->
            when(result){
                is Resource.Success -> {
                    _dishesState.update {
                        result.data?: emptyList()
                    }
                    _loadingState.value = false
                }
                is Resource.Loading -> {
                    _loadingState.value = true
                }
                is Resource.Error -> {
                    _loadingState.value = false
                    _messageState.emit(result.message?: "Something went wrong")
                }
            }
        }.launchIn(viewModelScope + Dispatchers.IO)
    }

    fun selectTag(name: String){
        val categories = arrayListOf<CategoryModel>()
        categoriesState.value.forEach {
            categories.add(
                CategoryModel(
                    id = it.id,
                    name = it.name,
                    isSelected = it.name == name
                )
            )
        }
        _categoriesState.update{
            categories
        }
    }

}

@Suppress("UNCHECKED_CAST")
@Singleton
class ViewModelFactory (
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getDishesUseCase: GetDishesUseCase
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DishesViewModel(getCategoriesUseCase, getDishesUseCase) as T
    }

}