package com.example.petshopapptp3.data.remote

data class LoginResponse(
    val token: String,
    val userId: Int,
    val name: String
)