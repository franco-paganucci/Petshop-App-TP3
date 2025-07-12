package com.example.petshopapptp3.di

import com.example.petshopapptp3.data.remote.ApiService
import com.example.petshopapptp3.data.remote.RetroFitInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService = RetroFitInstance.api
}
