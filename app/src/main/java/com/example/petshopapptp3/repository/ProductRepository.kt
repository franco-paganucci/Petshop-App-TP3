package com.example.petshopapptp3.repository

import com.example.petshopapptp3.data.remote.api.ApiService
import com.example.petshopapptp3.data.remote.dto.Product
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getFilteredProducts(): List<Product> {
        val response = apiService.getProducts()
        return response.products.filter {
            it.title.contains("Eggs", ignoreCase = true) ||
                    it.title.contains("Fish", ignoreCase = true) ||
                    it.title.contains("Honey", ignoreCase = true) ||
                    it.title.contains("Ice", ignoreCase = true) ||
                    it.title.contains("Kiwi", ignoreCase = true) ||
                    it.title.contains("Green", ignoreCase = true)
        }
    }
}
