package com.example.meli_challenge

import androidx.lifecycle.ViewModel
import com.example.meli_challenge.domain.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SearchViewModel : ViewModel() {

    private var _products = MutableStateFlow<List<Product>>(emptyList())
    val products:StateFlow<List<Product>> = _products

    init {
        _products.value = listOf(Product(name = "primero"),Product(name = "segundo"))
    }

    fun searchProducts(query: String) {

    }

}