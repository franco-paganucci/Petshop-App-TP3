package com.example.petshopapptp3.viewModel

import javax.inject.Inject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

import com.google.firebase.auth.FirebaseUser

import dagger.hilt.android.lifecycle.HiltViewModel

import com.example.petshopapptp3.repository.AuthRepository
import com.example.petshopapptp3.data.remote.UserProfile

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _authState = MutableStateFlow<Result<FirebaseUser?>?>(null)
    val authState: StateFlow<Result<FirebaseUser?>?> = _authState
    private val _userProfile = MutableStateFlow<Result<UserProfile>?>(null)
    val userProfile: StateFlow<Result<UserProfile>?> = _userProfile
    private val _updateState = MutableStateFlow<Result<Void?>?>(null)
    val updateState: StateFlow<Result<Void?>?> = _updateState

    fun register(email: String, password: String, fullName: String) {
        viewModelScope.launch {
            authRepository.registerUser(email, password, fullName).collect {
                _authState.value = it
            }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            authRepository.loginUser(email, password).collect {
                _authState.value = it
            }
        }
    }

    fun clearState() {
        _authState.value = null
    }

    fun loadUserProfile() {
        viewModelScope.launch {
            authRepository.getCurrentUserProfile().collect {
                _userProfile.value = it
            }
        }
    }

    fun updateUserProfile(fullName: String, email: String) {
        viewModelScope.launch {
            val result = authRepository.updateUserProfile(fullName, email)
            _updateState.value = result
        }
    }
}
