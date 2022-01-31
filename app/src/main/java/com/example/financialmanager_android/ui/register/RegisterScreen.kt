package com.example.financialmanager_android.ui.register

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.financialmanager_android.R
import com.example.financialmanager_android.ui.theme.MainPurple

@Composable
fun RegisterScreen(viewModel: RegisterViewModel = RegisterViewModel()) {
    val firstNameState = viewModel.firstNameState.observeAsState("")
    val lastNameState = viewModel.lastNameState.observeAsState("")
    val emailState = viewModel.emailState.observeAsState("")
    val passwordState = viewModel.passwordState.observeAsState("")
    val confirmPasswordState = viewModel.confirmPasswordState.observeAsState("")

    val firstNameValid = viewModel.firstNameError.observeAsState("")
    val lastNameValid = viewModel.lastNameError.observeAsState("")
    val emailValid = viewModel.emailError.observeAsState("")
    val passValid = viewModel.passwordError.observeAsState("")
    val confirmPassValid = viewModel.confirmPasswordError.observeAsState("")

    Surface(color = MaterialTheme.colors.MainPurple, modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
        ) {
            // Back button
            Image(
                painter = painterResource(id = R.drawable.btn_back),
                contentDescription = "",
                modifier = Modifier
                    .padding(20.dp)
                    .size(60.dp)
            )

            // Login background
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
                            text = "Register",
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.h3,
                            color = MaterialTheme.colors.MainPurple,
                            modifier = Modifier.padding(20.dp)
                        )


                        InputField(
                            title = "First name",
                            leadingIcon = Icons.Filled.Person,
                            state = firstNameState.value,
                            err = firstNameValid.value,
                            keyboardType = KeyboardType.Text
                        ) { value ->
                            viewModel.onTextChange(
                                value,
                                RegisterViewModel.RegisterFields.FIRSTNAME
                            )
                        }
                        InputField(
                            title = "Last name",
                            leadingIcon = Icons.Filled.Person,
                            state = lastNameState.value,
                            err = lastNameValid.value,
                            keyboardType = KeyboardType.Text
                        ) { value ->
                            viewModel.onTextChange(
                                value,
                                RegisterViewModel.RegisterFields.LASTNAME
                            )
                        }
                        InputField(
                            title = "Email",
                            leadingIcon = Icons.Filled.Email,
                            state = emailState.value,
                            err = emailValid.value,
                            keyboardType = KeyboardType.Email
                        ) { value ->
                            viewModel.onTextChange(
                                value,
                                RegisterViewModel.RegisterFields.EMAIL
                            )
                        }
                        InputField(
                            title = "Password",
                            leadingIcon = Icons.Filled.Lock,
                            state = passwordState.value,
                            err = passValid.value,
                            keyboardType = KeyboardType.Password
                        ) { value ->
                            viewModel.onTextChange(
                                value,
                                RegisterViewModel.RegisterFields.PASSWORD
                            )
                        }
                        InputField(
                            title = "Confirm password",
                            leadingIcon = Icons.Filled.Lock,
                            state = confirmPasswordState.value,
                            err = confirmPassValid.value,
                            keyboardType = KeyboardType.Password
                        ) { value ->
                            viewModel.onTextChange(
                                value,
                                RegisterViewModel.RegisterFields.CONFIRMPASSWORD
                            )
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Button(
                            onClick = { viewModel.onSubmit() },
                            shape = RoundedCornerShape(20.dp),
                        ) {
                            Text(
                                text = "Register",
                                modifier = Modifier.padding(horizontal = 30.dp, vertical = 5.dp),
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Row(modifier = Modifier.padding(20.dp)) {
                            Text(text = "Do you have an account? ")
                            Text(text = "Log in", color = MaterialTheme.colors.MainPurple)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun InputField(
    title: String,
    leadingIcon: ImageVector,
    state: String,
    err: String?,
    keyboardType: KeyboardType,
    onChange: (String) -> Unit
) {
    var passwordVisibility by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(top = 20.dp)) {
        Text(
            text = title,
            modifier = Modifier.padding(bottom = 8.dp),
            fontWeight = FontWeight.Bold
        )

        if (!err.isNullOrEmpty()) {
            Text(text = err, color = Color.Red)
        }

        OutlinedTextField(
            value = state,
            leadingIcon = {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = title,
                    tint = Color.Black

                )
            },
            trailingIcon = {
                if(keyboardType != KeyboardType.Password) {
                    if (!err.isNullOrEmpty() )
                        Icon(
                            imageVector = Icons.Filled.Warning,
                            contentDescription = "error",
                            tint = Color.Red
                        )
                } else {
                    val icon = if (passwordVisibility)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff

                    IconButton(onClick = {
                        passwordVisibility = !passwordVisibility
                    }) {
                        Icon(imageVector  = icon, "")
                    }
                }
            },
            placeholder = { Text(text = title) },
            isError = !err.isNullOrEmpty(),
            onValueChange = { value -> onChange.invoke(value) },
            shape = RoundedCornerShape(15.dp),
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            visualTransformation = if(keyboardType == KeyboardType.Password && !passwordVisibility) {PasswordVisualTransformation()} else VisualTransformation.None
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    RegisterScreen()
}