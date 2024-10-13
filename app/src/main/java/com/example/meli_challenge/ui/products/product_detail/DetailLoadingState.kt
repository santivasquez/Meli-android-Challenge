package com.example.meli_challenge.ui.products.product_detail

import com.example.meli_challenge.domain.model.Product

sealed class DetailLoadingState {
    data object Loading : DetailLoadingState()
    data class Success(val product: Product) : DetailLoadingState()
    data class Error(val message: String) : DetailLoadingState()
}
