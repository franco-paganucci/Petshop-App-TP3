package com.example.petshopapptp3

import android.app.Application
import androidx.room.Room
import com.example.petshopapptp3.data.local.AppDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PetshopApp : Application() {
    lateinit var database: AppDatabase
        private set

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "cart_database"
        ).build()
    }
}
