package com.example.petshopapptp3.repository

import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

import com.example.petshopapptp3.data.local.CartDao
import com.example.petshopapptp3.data.local.CartItemEntity
import com.example.petshopapptp3.data.remote.api.ApiService
import com.example.petshopapptp3.data.remote.dto.AddToCartRequest
import com.example.petshopapptp3.data.remote.dto.CartProduct
import com.example.petshopapptp3.data.remote.dto.CartResponse

class CartRepository @Inject constructor(
    private val cartDao: CartDao,
    private val apiService: ApiService
) {
    val cartItems: Flow<List<CartItemEntity>> = cartDao.getAllCartItems()
    val cartTotal: Flow<Double?> = cartDao.getCartTotal()

    suspend fun addCartItem(item: CartItemEntity) {
        cartDao.insertCartItem(item)
    }

    suspend fun addOrUpdateCartItem(item: CartItemEntity) {
        val existingItems = cartDao.getCartItemById(item.id)
        if (existingItems != null) {
            val newQuantity = existingItems.quantity + item.quantity
            cartDao.updateCartItemQuantity(item.id, newQuantity)
        } else {
            cartDao.insertCartItem(item)
        }
    }

    suspend fun updateItemQuantity(itemId: Int, quantity: Int) {
        cartDao.updateCartItemQuantity(itemId, quantity)
    }

    suspend fun removeCartItemById(itemId: Int) {
        cartDao.deleteCartItemById(itemId)
    }

    suspend fun clearCart() {
        cartDao.clearCart()
    }

    // --- API ---
    suspend fun submitCart(userId: Int, products: List<CartProduct>): CartResponse {
        return apiService.addToCart(AddToCartRequest(userId = userId, products = products))
    }

    suspend fun fetchCart(cartId: Int): CartResponse {
        return apiService.getCartById(cartId)
    }

    suspend fun deleteCart(cartId: Int) {
        apiService.deleteCart(cartId)
    }
}
