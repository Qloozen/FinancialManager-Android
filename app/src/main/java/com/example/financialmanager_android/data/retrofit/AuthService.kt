package com.example.financialmanager_android.data.retrofit

import com.example.financialmanager_android.data.models.RegisterBody
import com.example.financialmanager_android.data.models.RegisterResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

// Defining the connection to the REST API. Service will be used in the repository
class AuthService {
    private var client: AuthEndpoints

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://qiangloozen.nl/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        client = retrofit.create(AuthEndpoints::class.java)
    }


    suspend fun register(body: RegisterBody): RegisterResponse {
        return client.register(body);
    }
}

interface AuthEndpoints {
    @POST("Auth/Register")
    suspend fun register(@Body body: RegisterBody): RegisterResponse
}