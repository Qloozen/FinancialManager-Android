package com.example.financialmanager_android.data.models

data class RegisterResponse (val firstName: String, val lastName: String, val email: String, val token: String);

data class RegisterBody (val firstName: String, val lastName: String, val email: String, val password: String);