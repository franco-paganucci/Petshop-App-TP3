package com.example.petshopapptp3.viewModel

import javax.inject.Inject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

import com.example.petshopapptp3.data.local.CartItemEntity
import com.example.petshopapptp3.data.remote.dto.CartResponse
import com.example.petshopapptp3.data.remote.dto.CartProduct
import com.example.petshopapptp3.data.remote.dto.Product
import com.example.petshopapptp3.repository.CartRepository

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: CartRepository
) : ViewModel() {

    val localCartItems = repository.cartItems

    private val _cart = MutableStateFlow<CartResponse?>(null)
    val cart = _cart.asStateFlow()

    private var currentCartId: Int? = null

    private val cartProducts = mutableListOf<CartProduct>()

    fun addProductToCart(product: Product, quantity: Int) {
        viewModelScope.launch {
            val entity = CartItemEntity(
                id = product.id,
                title = product.title,
                price = product.price,
                quantity = quantity,
                thumbnail = product.thumbnail
            )
            repository.addOrUpdateCartItem(entity)

            val index = cartProducts.indexOfFirst { it.id == product.id }
            if (index != -1) {
                val existing = cartProducts[index]
                cartProducts[index] = existing.copy(quantity = existing.quantity + quantity)
            } else {
                cartProducts.add(CartProduct(id = product.id, quantity = quantity))
            }
        }
    }

    fun submitCartToApi() {
        viewModelScope.launch {
            try {
                val response = repository.submitCart(
                    userId = 1,
                    products = cartProducts
                )
                _cart.value = response
                currentCartId = response.id
                saveCartToLocal(response)
                cartProducts.clear()
                println("Carrito enviado y limpiado")
            } catch (e: Exception) {
                println("Error al enviar carrito: ${e.message}")
            }
        }
    }

    fun fetchCart() {
        viewModelScope.launch {
            try {
                currentCartId?.let { id ->
                    val response = repository.fetchCart(id)
                    _cart.value = response
                    println("Carrito cargado: ${response.products}")
                } ?: println("No hay carrito activo")
            } catch (e: Exception) {
                println("Error al obtener el carrito: ${e.message}")
            }
        }
    }

    fun deleteCart(cartId: Int) {
        viewModelScope.launch {
            try {
                repository.deleteCart(cartId)
                _cart.value = null
                currentCartId = null
                cartProducts.clear()
                clearLocalCart()
                println("Carrito eliminado")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun saveCartToLocal(cart: CartResponse) {
        viewModelScope.launch {
            repository.clearCart()
            cart.products.forEach {
                val entity = CartItemEntity(
                    id = it.id,
                    title = it.title,
                    price = it.price,
                    quantity = it.quantity,
                    thumbnail = it.thumbnail
                )
                repository.addCartItem(entity)
            }
        }
    }

    fun clearLocalCart() {
        viewModelScope.launch {
            repository.clearCart()
            cartProducts.clear()
        }
    }

    fun clearEverything() {
        viewModelScope.launch {
            repository.clearCart()
            cartProducts.clear()
            _cart.value = null
            currentCartId = null
        }
    }
}
