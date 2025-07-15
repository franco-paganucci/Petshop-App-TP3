package com.example.petshopapptp3.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import com.example.petshopapptp3.data.remote.dto.PaymentMethod

private const val TAG = "PaymentRepo"

class PaymentRepository @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
) {

    fun savePaymentMethod(paymentMethod: PaymentMethod): Flow<Result<Void?>> = flow {
        try {
            val currentUser = auth.currentUser
            if (currentUser == null) {
                Log.e(TAG, "User not authenticated when trying to save payment method.")
                emit(Result.failure(Exception("Usuario no autenticado")))
                return@flow
            }
            Log.d(TAG, "Authenticated user UID: ${currentUser.uid}")

            val dataToSave = mutableMapOf<String, Any?>()
            dataToSave["type"] = paymentMethod.type
            dataToSave["cardName"] = paymentMethod.cardName
            dataToSave["cardNumberLast4"] = paymentMethod.cardNumberLast4
            dataToSave["expiryDate"] = paymentMethod.expiryDate
            dataToSave["userId"] = currentUser.uid
            dataToSave["isDefault"] = paymentMethod.isDefault

            val docRef = if (paymentMethod.id.isNullOrBlank()) {
                Log.d(TAG, "Creating new payment method document (auto-ID).")
                firestore.collection("users").document(currentUser.uid)
                    .collection("paymentMethods").document()
            } else {
                Log.d(TAG, "Updating existing payment method document with ID: ${paymentMethod.id}")
                firestore.collection("users").document(currentUser.uid)
                    .collection("paymentMethods").document(paymentMethod.id)
            }
            Log.d(TAG, "Firestore document path: ${docRef.path}")

            docRef.set(dataToSave).await()
            Log.d(TAG, "Payment method saved successfully to Firestore! Document ID: ${docRef.id}")
            emit(Result.success(null))
        } catch (e: Exception) {
            Log.e(TAG, "Error saving payment method: ${e.message}", e)
            emit(Result.failure(e))
        }
    }

    fun getPaymentMethods(): Flow<Result<List<PaymentMethod>>> = flow {
        try {
            val currentUser = auth.currentUser
            if (currentUser == null) {
                Log.e(TAG, "User not authenticated when trying to get payment methods.")
                emit(Result.failure(Exception("Usuario no autenticado")))
                return@flow
            }
            Log.d(TAG, "Attempting to get payment methods for UID: ${currentUser.uid}")

            val snapshot = firestore.collection("users").document(currentUser.uid)
                .collection("paymentMethods")
                .get()
                .await()

            val paymentMethods = snapshot.documents.mapNotNull { document ->
                Log.d(TAG, "Document ID from path: ${document.id}")
                Log.d(TAG, "Document Data from Firestore: ${document.data}") // THIS IS KEY!

                val method = document.toObject(PaymentMethod::class.java)
                Log.d(TAG, "Deserialized PaymentMethod: $method (from doc: ${document.id})")
                method
            }
            Log.d(TAG, "Successfully loaded ${paymentMethods.size} payment methods.")
            emit(Result.success(paymentMethods))
        } catch (e: Exception) {
            Log.e(TAG, "Error loading payment methods: ${e.message}", e)
            emit(Result.failure(e))
        }
    }

    suspend fun deletePaymentMethod(methodId: String): Result<Void?> {
        return try {
            val currentUser = auth.currentUser ?: throw Exception("Usuario no autenticado")
            firestore.collection("users").document(currentUser.uid)
                .collection("paymentMethods").document(methodId)
                .delete()
                .await()
            Log.d(TAG, "Payment method with ID $methodId deleted successfully.")
            Result.success(null)
        } catch (e: Exception) {
            Log.e(TAG, "Error deleting payment method $methodId: ${e.message}", e)
            Result.failure(e)
        }
    }
}