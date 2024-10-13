package com.example.meli_challenge.data.network.model

import com.example.meli_challenge.domain.model.Category
import com.google.gson.annotations.SerializedName


data class CategoryResponse (
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,

    )

fun CategoryResponse.toCategory(): Category {
    return Category(
        id = this.id,
        name = this.name,
    )
}
