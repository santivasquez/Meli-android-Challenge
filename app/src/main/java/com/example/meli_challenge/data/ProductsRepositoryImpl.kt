package com.example.meli_challenge.data

import android.util.Log
import com.example.meli_challenge.data.network.ProductsApiService
import com.example.meli_challenge.data.network.model.toCategory
import com.example.meli_challenge.data.network.model.toProduct
import com.example.meli_challenge.domain.ProductsRepository
import com.example.meli_challenge.domain.model.Category
import com.example.meli_challenge.domain.model.Product
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(private val productsApiService: ProductsApiService) :
    ProductsRepository {
    override suspend fun getProducts(query: String, category: String): List<Product>? {
        runCatching { productsApiService.getProducts(query = query, category = category) }
            .onSuccess { response ->
                return response.results.map { responseProduct ->
                    responseProduct.toProduct() //TODO Create a mapper function
                }
            }
            .onFailure { Log.i("Service Error", "Error: ${it.message}") }

        return null
    }

    override suspend fun getProductById(productId: String): Product? {
        runCatching { productsApiService.getProductById(productId) }
            .onSuccess { response ->
                return response.toProduct() //TODO Create a mapper function
            }
            .onFailure { Log.i("Service Error", "Error: ${it.message}") }

        return null
    }

    override suspend fun getCategories(): List<Category>? {
        runCatching { productsApiService.getCategories() }
            .onSuccess { response ->
                return response.map { responseCategory -> responseCategory.toCategory() }
            }
            .onFailure { Log.i("Service Error", "Error: ${it.message}") }

        return null
    }
}
