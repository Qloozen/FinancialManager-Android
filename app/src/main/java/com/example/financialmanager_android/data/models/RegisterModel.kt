package com.example.financialmanager_android.data.models

// API response
data class RegisterResponse (val userId: Int, val firstName: String, val lastName: String, val email: String, val token: String);

// API body
data class RegisterBody (val firstName: String, val lastName: String, val email: String, val password: String);