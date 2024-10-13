package com.example.meli_challenge.domain.useCase

import com.example.meli_challenge.domain.ProductsRepository
import javax.inject.Inject

class GetProductUseCase @Inject constructor(private val productsRepository: ProductsRepository) {
    suspend operator fun invoke(query: String, category: String) = productsRepository.getProducts(query, category) // sobreescribir algunas implementaciones de la clase

}