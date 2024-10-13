package com.example.meli_challenge.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meli_challenge.domain.ProductsRepository
import com.example.meli_challenge.domain.model.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val productsRepository: ProductsRepository) : ViewModel() {

    private var _categories = MutableStateFlow<List<Category>?>(null)
    val categories: StateFlow<List<Category>?> = _categories
    var selectedCategoryPosition: Int = 0

    init {
        searchCategories()
    }

    private fun searchCategories() {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val result =  productsRepository.getCategories()
                if (result != null){
                    _categories.value = listOf(Category("","Select a Category")) + result
                } else {
                    // TODO: Handle error
                }
            }
        }

    }

}
