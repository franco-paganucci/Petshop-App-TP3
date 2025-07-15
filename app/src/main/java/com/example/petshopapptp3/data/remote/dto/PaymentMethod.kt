package com.example.petshopapptp3.data.remote.dto

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.PropertyName

data class PaymentMethod(
    @DocumentId
    val id: String? = null,
    val type: String = "",
    val cardName: String? = null,
    val cardNumberLast4: String? = null,
    val expiryDate: String? = null,
    val userId: String? = null,
    @get:PropertyName("default")
    val isDefault: Boolean = false
)