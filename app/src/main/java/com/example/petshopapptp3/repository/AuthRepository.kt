package com.example.petshopapptp3.repository

import com.example.petshopapptp3.data.remote.dto.UserProfile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) {

    fun registerUser(email: String, password: String, fullName: String): Flow<Result<FirebaseUser?>> = flow {
        try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            val user = result.user

            if (user != null) {
                val userProfile = UserProfile(
                    uid = user.uid,
                    fullName = fullName,
                    email = email
                )

                firestore.collection("users")
                    .document(user.uid)
                    .set(userProfile)
                    .await()
            }

            emit(Result.success(user))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    fun loginUser(email: String, password: String): Flow<Result<FirebaseUser?>> = flow {
        try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            emit(Result.success(result.user))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    fun getCurrentUserProfile(): Flow<Result<UserProfile>> = flow {
        try {
            val currentUser = auth.currentUser ?: throw Exception("Usuario no autenticado")
            val snapshot = firestore.collection("users").document(currentUser.uid).get().await()
            val profile = snapshot.toObject(UserProfile::class.java) ?: throw Exception("Perfil no encontrado")
            emit(Result.success(profile))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    suspend fun updateUserProfile(fullName: String, email: String): Result<Void?> {
        return try {
            val currentUser = auth.currentUser ?: throw Exception("Usuario no autenticado")
            val updates = mapOf(
                "fullName" to fullName,
                "email" to email
            )
            firestore.collection("users")
                .document(currentUser.uid)
                .update(updates)
                .await()
            Result.success(null)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}

