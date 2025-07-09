package com.example.petshopapptp3

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import dagger.hilt.android.AndroidEntryPoint

import com.example.petshopapptp3.ui.theme.PetshopAppTP3Theme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PetshopAppTP3Theme {
                MainScreen()
            }
        }
    }
}
