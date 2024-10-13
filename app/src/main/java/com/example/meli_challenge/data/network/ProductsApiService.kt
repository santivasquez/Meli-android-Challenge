package com.example.meli_challenge.data.network

import com.example.meli_challenge.data.network.model.CategoryResponse
import com.example.meli_challenge.data.network.model.ProductResponse
import com.example.meli_challenge.data.network.model.ProductsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductsApiService {
    @GET("sites/{siteId}/search?")
    suspend fun getProducts(
        @Path("siteId") siteId: String = "MCO",
        @Query("q") query: String = "",
        @Query("limit") limit: Int = 50,
        @Query("sort") sort: String = "price_desc",
        @Query("category") category: String = "",
    ): ProductsResponse

    @GET("items/{id}")
    suspend fun getProductById(
        @Path("id") productId: String,
    ): ProductResponse

    @GET("sites/{siteId}/categories")
    suspend fun getCategories(
        @Path("siteId") siteId: String = "MCO",
    ): List<CategoryResponse>
}
