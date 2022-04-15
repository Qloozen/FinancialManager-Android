package com.example.financialmanager_android.ui.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.financialmanager_android.ui.theme.MainPurple

@Composable
fun LoginScreen(navController: NavHostController) {
    Surface(color = MaterialTheme.colors.MainPurple, modifier = Modifier.fillMaxSize()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "FinancialManager",
                style = MaterialTheme.typography.h4,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 25.dp)
            )

            Spacer(modifier = Modifier.height(100.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                elevation = 12.dp,
                shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
                backgroundColor = Color.White,
            ) {
                LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
                    item {
                        Text(
                            text = "Login",
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.h3,
                            color = MaterialTheme.colors.MainPurple,
                            modifier = Modifier.padding(20.dp)
                        )

                        InputField(
                            title = "E-mail",
                            leadingIcon = Icons.Filled.Email,
                            state = "",
                            err = "",
                            keyboardType = KeyboardType.Email,
                        ) {}


                        InputField(
                            title = "Password",
                            leadingIcon = Icons.Filled.Lock,
                            state = "",
                            err = "",
                            keyboardType = KeyboardType.Password
                        ) {}

                        Spacer(modifier = Modifier.height(20.dp))

                        Button(
                            onClick = { },
                            shape = RoundedCornerShape(20.dp),
                        ) {
                            Text(
                                text = "Login",
                                modifier = Modifier.padding(horizontal = 30.dp, vertical = 5.dp),
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Row(modifier = Modifier.padding(20.dp)) {
                            Text(text = "Don't have an account? ")
                            Text(text = "Register here",
                                color = MaterialTheme.colors.MainPurple,
                                modifier = Modifier.clickable {
                                    navController.navigate("register_screen")
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}