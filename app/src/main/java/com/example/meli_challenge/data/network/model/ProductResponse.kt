package com.example.meli_challenge.data.network.model

import com.example.meli_challenge.domain.model.Product
import com.google.gson.annotations.SerializedName

data class ProductsResponse (
    @SerializedName("results")
    val results: List<ProductResponse>
)

data class ProductResponse (
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val name: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("thumbnail")
    val thumbnail: String,

)

fun ProductResponse.toProduct(): Product {
    return Product(
        id = this.id,
        name = this.name,
        price = this.price,
        thumbnail = this.thumbnail
    )
}
