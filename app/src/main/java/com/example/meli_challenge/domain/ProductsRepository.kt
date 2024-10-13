package com.example.meli_challenge.domain

import com.example.meli_challenge.data.network.model.ProductResponse
import com.example.meli_challenge.domain.model.Product

interface ProductsRepository {
    suspend fun getProducts(query: String): List<Product>?

    suspend fun getProductById(productId: String): Product?
}