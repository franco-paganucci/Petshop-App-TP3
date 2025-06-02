package com.example.petshopapptp3.data

import com.example.petshopapptp3.data.remote.ProductResponse
import retrofit2.http.GET

interface ApiService {
    @GET("products")
    suspend fun getProducts(): ProductResponse
}