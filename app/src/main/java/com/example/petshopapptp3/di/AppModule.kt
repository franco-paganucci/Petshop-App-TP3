package com.example.petshopapptp3.di

import android.app.Application

import androidx.room.Room

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import com.example.petshopapptp3.data.local.AppDatabase
import com.example.petshopapptp3.data.local.CartDao
import com.example.petshopapptp3.data.remote.api.ApiService
import com.example.petshopapptp3.repository.CartRepository

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDatabase =
        Room.databaseBuilder(app, AppDatabase::class.java, "cart_database")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideCartDao(db: AppDatabase): CartDao = db.cartDao()

    @Provides
    @Singleton
    fun provideCartRepository(cartDao: CartDao, apiService: ApiService): CartRepository =
        CartRepository(cartDao, apiService)
}


