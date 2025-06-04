package com.example.petshopapptp3.repository

import com.example.petshopapptp3.data.local.CartDao
import com.example.petshopapptp3.data.local.CartItemEntity
import kotlinx.coroutines.flow.Flow

class CartRepository(private val cartDao: CartDao) {
    val cartItems: Flow<List<CartItemEntity>> = cartDao.getAllCartItems()

    suspend fun addCartItem(item: CartItemEntity) {
        cartDao.insertCartItem(item)
    }

    suspend fun clearCart() {
        cartDao.clearCart()
    }
}
