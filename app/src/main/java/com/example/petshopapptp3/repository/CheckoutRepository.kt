package com.example.petshopapptp3.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

import kotlinx.coroutines.tasks.await

import java.util.Date
import javax.inject.Inject

import com.example.petshopapptp3.data.remote.dto.Checkout
import com.example.petshopapptp3.data.remote.dto.CheckoutItem

class CheckoutRepository @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
) {

    suspend fun saveCheckout(total: Double, paymentMethod: String, items: List<CheckoutItem>): Result<Void?> {
        return try {
            val userId = auth.currentUser?.uid ?: throw Exception("Usuario no autenticado")
            val checkout = Checkout(
                userId = userId,
                date = Date(),
                total = total,
                paymentMethod = paymentMethod,
                items = items
            )

            firestore.collection("checkouts")
                .add(checkout)
                .await()

            Result.success(null)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
