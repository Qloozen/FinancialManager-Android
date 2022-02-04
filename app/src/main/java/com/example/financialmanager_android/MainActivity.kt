package com.example.financialmanager_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.financialmanager_android.ui.register.LoginScreen
import com.example.financialmanager_android.ui.register.RegisterScreen
import com.example.financialmanager_android.ui.theme.FinancialManagerAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainNavigation()
        }
    }

    @Composable
    private fun MainNavigation() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "register_screen") {
            composable("register_screen") {
                RegisterScreen(navController = navController)
            }
            composable("login_screen") {
                LoginScreen(navController)
            }
        }
    }
}