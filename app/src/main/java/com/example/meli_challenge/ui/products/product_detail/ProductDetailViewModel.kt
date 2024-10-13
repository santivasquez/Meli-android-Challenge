package com.example.meli_challenge.ui.products.product_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meli_challenge.domain.ProductsRepository
import com.example.meli_challenge.domain.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(private val productsRepository: ProductsRepository) : ViewModel() {

    private var _product = MutableStateFlow<Product?>(null)
    val product: StateFlow<Product?> = _product

    private var _state = MutableStateFlow<DetailLoadingState>(DetailLoadingState.Loading)
    val state: StateFlow<DetailLoadingState> = _state



    fun searchProduct(productId: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val result =  productsRepository.getProductById(productId)
                if (result != null){
                    _product.value = result
                    _state.value = DetailLoadingState.Success(result)
                } else {
                    _state.value = DetailLoadingState.Error("Error")
                }
            }
        }

    }

}
