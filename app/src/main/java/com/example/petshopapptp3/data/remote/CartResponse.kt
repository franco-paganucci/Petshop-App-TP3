package com.example.petshopapptp3.data.remote

data class CartResponse(
    val id: Int,
    val userId: Int,
    val total: Double,
    val discountedTotal: Double,
    val products: List<CartItemResponse>
)

data class CartItemResponse(
    val id: Int,
    val title: String,
    val price: Double,
    val quantity: Int,
    val total: Double,
    val thumbnail: String
)