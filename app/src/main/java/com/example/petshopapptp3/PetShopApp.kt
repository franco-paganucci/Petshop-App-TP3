package com.example.petshopapptp3

import android.app.Application
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PetshopApp : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this) // 🔥 Firebase inicializado
        // Room se maneja con Hilt
    }
}
