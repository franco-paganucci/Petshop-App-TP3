package com.example.petshopapptp3.viewModel

import javax.inject.Inject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

import dagger.hilt.android.lifecycle.HiltViewModel

import com.example.petshopapptp3.data.remote.dto.Product
import com.example.petshopapptp3.repository.ProductRepository

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            try {
                val filteredProducts = repository.getFilteredProducts()
                _products.value = filteredProducts
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

