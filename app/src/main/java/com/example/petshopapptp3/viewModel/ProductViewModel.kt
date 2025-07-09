package com.example.petshopapptp3.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

import com.example.petshopapptp3.data.remote.Product
import com.example.petshopapptp3.data.remote.RetroFitInstance

class ProductViewModel : ViewModel() {
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            try {
                val response = RetroFitInstance.api.getProducts()
                println("Productos recibidos: ${response.products.size}")
                _products.value = response.products.filter {
                    it.title.contains("Eggs", ignoreCase = true) ||
                            it.title.contains("Fish", ignoreCase = true) ||
                            it.title.contains("Honey", ignoreCase = true) ||
                            it.title.contains("Ice", ignoreCase = true) ||
                            it.title.contains("Kiwi", ignoreCase = true) ||
                            it.title.contains("Green", ignoreCase = true)

                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}