package com.example.meli_challenge.data.network

import com.example.meli_challenge.data.network.model.ProductResponse
import com.example.meli_challenge.data.network.model.ProductsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductsApiService {
    @GET("sites/{siteId}/search?category=MLA1055")
    suspend fun getProducts(
        @Path("siteId") siteId: String = "MLA",
        //@Query("q") query: String = "",
        @Query("limit") limit: Int = 20,
        @Query("sort") sort: String = "price_desc",
    ): ProductsResponse

    @GET("items/{id}")
    suspend fun getProductById(
        @Path("id") productId: String,
    ): ProductResponse

}