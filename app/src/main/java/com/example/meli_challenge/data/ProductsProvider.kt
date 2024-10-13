package com.example.meli_challenge.data

import com.example.meli_challenge.domain.model.Product
import javax.inject.Inject


class ProductsProvider @Inject constructor() {
    fun getProducts():List<Product>{
        return listOf()
    }
}
