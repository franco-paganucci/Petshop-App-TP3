package com.example.petshopapptp3.data.remote.cart

data class AddToCartRequest(
    val userId: Int = 1,
    val products: List<CartProduct>
)

data class CartProduct(
    val id: Int,
    val quantity: Int
)