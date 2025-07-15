package com.example.petshopapptp3.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import android.util.Log

import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

import javax.inject.Inject

import com.example.petshopapptp3.data.local.CartItemEntity
import com.example.petshopapptp3.data.remote.dto.CartProduct
import com.example.petshopapptp3.data.remote.dto.CartResponse
import com.example.petshopapptp3.data.remote.dto.CheckoutItem
import com.example.petshopapptp3.data.remote.dto.Product
import com.example.petshopapptp3.data.remote.dto.PaymentMethod
import com.example.petshopapptp3.repository.CartRepository
import com.example.petshopapptp3.repository.CheckoutRepository
import com.example.petshopapptp3.repository.PaymentRepository

private const val TAG_VM = "CartViewModel"

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: CartRepository,
    private val checkoutRepository: CheckoutRepository,
    private val paymentRepository: PaymentRepository
) : ViewModel() {

    private val _localCartItems = MutableStateFlow<List<CartItemEntity>>(emptyList())
    val localCartItems: StateFlow<List<CartItemEntity>> = _localCartItems.asStateFlow()

    private val _cartTotal = MutableStateFlow<Double?>(0.0)
    val cartTotal: StateFlow<Double?> = _cartTotal.asStateFlow()


    private val _cart = MutableStateFlow<CartResponse?>(null)
    val cart = _cart.asStateFlow()

    private val _paymentMethods = MutableStateFlow<List<PaymentMethod>>(emptyList())
    val paymentMethods: StateFlow<List<PaymentMethod>> = _paymentMethods.asStateFlow()

    private var currentCartId: Int? = null
    private val cartProducts = mutableListOf<CartProduct>()

    internal val _checkoutState = MutableStateFlow<Result<Void?>?>(null)
    val checkoutState: StateFlow<Result<Void?>?> = _checkoutState.asStateFlow()

    init {
        loadPaymentMethods()
        viewModelScope.launch {
            repository.cartItems.collectLatest { items ->
                _localCartItems.value = items
            }
        }
        viewModelScope.launch {
            repository.cartTotal.collectLatest { total ->
                _cartTotal.value = total
            }
        }
    }

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

    private fun saveCartToLocal(cart: CartResponse) {
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

    private fun clearEverything() {
        viewModelScope.launch {
            repository.clearCart()
            cartProducts.clear()
            _cart.value = null
            currentCartId = null
        }
    }

    // --- Checkout and Payment Methods ---
    fun saveCheckoutToFirestore(
        selectedPaymentMethod: PaymentMethod,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            _checkoutState.value = null
            val itemsInCart = localCartItems.firstOrNull() ?: emptyList()
            val totalAmount = cartTotal.firstOrNull() ?: 0.0

            if (itemsInCart.isEmpty()) {
                onError("El carrito está vacío.")
                return@launch
            }

            val checkoutItems = itemsInCart.map {
                CheckoutItem(
                    id = it.id,
                    title = it.title,
                    price = it.price,
                    quantity = it.quantity,
                    thumbnail = it.thumbnail
                )
            }

            val paymentMethodStringForFirestore = when (selectedPaymentMethod.type) {
                "Credit Card" -> "${selectedPaymentMethod.cardName} ending in ${selectedPaymentMethod.cardNumberLast4}"
                else -> selectedPaymentMethod.type
            }

            val result = checkoutRepository.saveCheckout(
                total = totalAmount,
                paymentMethod = paymentMethodStringForFirestore,
                items = checkoutItems
            )
            _checkoutState.value = result

            if (result.isSuccess) {
                clearEverything()
                onSuccess(selectedPaymentMethod.type)
            } else {
                onError(result.exceptionOrNull()?.localizedMessage ?: "Error desconocido al guardar checkout")
            }
        }
    }

    fun savePaymentMethod(paymentMethod: PaymentMethod, onSuccess: () -> Unit, onError: (String) -> Unit) {
        Log.d(TAG_VM, "CartViewModel.savePaymentMethod called for type: ${paymentMethod.type}")
        viewModelScope.launch {
            paymentRepository.savePaymentMethod(paymentMethod).collect { result ->
                if (result.isSuccess) {
                    Log.d(TAG_VM, "Payment method save SUCCESS. Calling onSuccess().")
                    onSuccess()
                    loadPaymentMethods()
                    Log.d(TAG_VM, "loadPaymentMethods() called after successful save.")
                } else {
                    val errorMessage = result.exceptionOrNull()?.localizedMessage ?: "Error desconocido al guardar método de pago"
                    Log.e(TAG_VM, "Payment method save FAILED: $errorMessage", result.exceptionOrNull())
                    onError(errorMessage)
                }
            }
        }
    }

    private fun loadPaymentMethods() {
        Log.d(TAG_VM, "loadPaymentMethods() initiated.")
        viewModelScope.launch {
            paymentRepository.getPaymentMethods().collect { result ->
                if (result.isSuccess) {
                    val methods = result.getOrNull() ?: emptyList()
                    _paymentMethods.value = methods
                    Log.d(TAG_VM, "Successfully loaded payment methods: ${methods.size} found.")
                    methods.forEach { Log.d(TAG_VM, "Loaded method: $it") }
                } else {
                    val errorMessage = result.exceptionOrNull()?.localizedMessage
                    Log.e(TAG_VM, "Error loading payment methods in VM: $errorMessage", result.exceptionOrNull())
                    _paymentMethods.value = emptyList()
                }
            }
        }
    }
}