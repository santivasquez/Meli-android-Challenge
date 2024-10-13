package com.example.meli_challenge.ui.products.product_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meli_challenge.domain.ProductsRepository
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
class ProductDetailViewModel @Inject constructor(private val productsRepository: ProductsRepository) : ViewModel() {

    private var _product = MutableStateFlow<Product?>(null)
    val product: StateFlow<Product?> = _product

    init {
        searchProduct("MLA1454844285")
    }

    fun searchProduct(productId: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val result =  productsRepository.getProductById(productId)
                if (result != null){
                    _product.value = result
                } else {
                    // TODO: Handle error
                }
            }
        }

    }

}
