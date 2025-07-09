package com.example.petshopapptp3.data.remote

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("products")
    suspend fun getProducts(): ProductResponse

    @GET("carts/1")
    suspend fun getCart(): CartResponse

    @POST("carts/add")
    suspend fun addToCart(
        @Body request: AddToCartRequest
    ): CartResponse

    @DELETE("carts/{id}")
    suspend fun deleteCart(
        @Path("id") cartId: Int
    ): CartResponse

    @DELETE("carts/{cartId}/items/{productId}")
    suspend fun deleteCartItem(
        @Path("cartId") cartId: Int,
        @Path("productId") productId: Int
    )

    @GET("carts/{id}")
    suspend fun getCartById(@Path("id") cartId: Int): CartResponse

}