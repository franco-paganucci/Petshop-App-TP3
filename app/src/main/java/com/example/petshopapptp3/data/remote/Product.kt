package com.example.petshopapptp3.data.remote

data class ProductResponse(
    val products: List<Product>
)

data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val thumbnail: String,
    val description: String,
    val category: String,
    val stock: Int,
)
