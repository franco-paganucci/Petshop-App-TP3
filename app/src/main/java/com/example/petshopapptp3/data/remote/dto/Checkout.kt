package com.example.petshopapptp3.data.remote.dto

import java.util.Date

data class CheckoutItem(
    val id: Int,
    val title: String,
    val price: Double,
    val quantity: Int,
    val thumbnail: String
)

data class Checkout(
    val userId: String,
    val date: Date,
    val total: Double,
    val paymentMethod: String,
    val items: List<CheckoutItem>
)