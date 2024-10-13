package com.example.meli_challenge.domain

import com.example.meli_challenge.data.network.model.ProductResponse
import com.example.meli_challenge.domain.model.Category
import com.example.meli_challenge.domain.model.Product

interface ProductsRepository {
    suspend fun getProducts(query: String, category: String): List<Product>?

    suspend fun getProductById(productId: String): Product?

    suspend fun getCategories(): List<Category>?

}