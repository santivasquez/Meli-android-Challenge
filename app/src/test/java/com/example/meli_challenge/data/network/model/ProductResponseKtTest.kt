package com.example.meli_challenge.data.network.model

import com.example.meli_challenge.motherObjects.ProductResponseObject.anyResponse
import org.junit.Test

class ProductResponseKtTest{

    @Test
    fun productResponseToProductDomain() {
        //Given
        val productResponse = anyResponse.copy(name = "anyTest")

        //When
        val product = productResponse.toProduct()

        //Then
        assert(product.name == productResponse.name)

    }

}