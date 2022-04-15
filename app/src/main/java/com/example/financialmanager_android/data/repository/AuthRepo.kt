package com.example.financialmanager_android.data.repository

import com.example.financialmanager_android.data.models.RegisterBody
import com.example.financialmanager_android.data.models.RegisterResponse
import com.example.financialmanager_android.data.retrofit.AuthService

// Used in the ViewModels
class AuthRepo(private val authService: AuthService = AuthService()) {

    // Calls the function in the service
    suspend fun register(body: RegisterBody): RegisterResponse {
        return authService.register(body);
    }

    // Singleton pattern
    companion object {
        @Volatile
        private var instance: AuthRepo? = null
        fun getInstance() = instance?: synchronized(this) {
            instance ?: AuthRepo().also { instance = it }
        }
    }

}