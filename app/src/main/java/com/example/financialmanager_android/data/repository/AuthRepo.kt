package com.example.financialmanager_android.data.repository

import com.example.financialmanager_android.data.models.RegisterBody
import com.example.financialmanager_android.data.models.RegisterResponse
import com.example.financialmanager_android.data.retrofit.AuthService

class AuthRepo(private val authService: AuthService = AuthService()) {

    suspend fun register(body: RegisterBody): RegisterResponse {
        return authService.register(body);
    }

    // Singleton
    companion object {
        @Volatile
        private var instance: AuthRepo? = null
        fun getInstance() = instance?: synchronized(this) {
            instance ?: AuthRepo().also { instance = it }
        }
    }

}