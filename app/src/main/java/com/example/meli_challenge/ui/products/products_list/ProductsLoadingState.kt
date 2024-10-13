package com.example.meli_challenge.ui.products.products_list

import com.example.meli_challenge.domain.model.Product

sealed class ProductsLoadingState {
    data object Loading : ProductsLoadingState()
    data class Success(val products: List<Product>) : ProductsLoadingState()
    data class Error(val message: String) : ProductsLoadingState()
}
