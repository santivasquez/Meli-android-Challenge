package com.example.meli_challenge.ui.products.products_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meli_challenge.domain.model.Product
import com.example.meli_challenge.domain.useCase.GetProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProductsListViewModel @Inject constructor(private val getProductUseCase: GetProductUseCase) :
    ViewModel() {

    private var _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    private var _state = MutableStateFlow<ProductsLoadingState>(ProductsLoadingState.Loading)
    val state: StateFlow<ProductsLoadingState> = _state


    fun searchProducts(query: String, category: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = getProductUseCase(query,category)
                if (result != null) {
                    _products.value = result
                    _state.value = ProductsLoadingState.Success(result)
                } else {
                    _state.value = ProductsLoadingState.Error("Error")
                }
            }
        }

    }

}
