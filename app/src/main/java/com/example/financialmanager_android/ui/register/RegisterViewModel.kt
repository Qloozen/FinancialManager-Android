package com.example.financialmanager_android.ui.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financialmanager_android.data.models.RegisterBody
import com.example.financialmanager_android.data.repository.AuthRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel(private val authRepo: AuthRepo = AuthRepo.getInstance()): ViewModel() {

    // Submit
    fun onSubmit() {
        if (validateForm()) {
            var firstName = firstNameState.value!!.trim().lowercase()
            var lastName = lastNameState.value!!.trim().lowercase()
            val email = emailState.value!!.trim().lowercase()
            val password = passwordState.value
            val body = RegisterBody(firstName = firstName, lastName = lastName, email = email, password = password!!)

            // This is a coroutine, for handling async tasks. ViewModelScope will keep the coroutine alive as long as the ViewModel
            viewModelScope.launch(Dispatchers.IO) { // IO = thread for networking/data, main = thread for UI, default = thread for CPU tasks
                val res = authRepo.register(body); // Executing API task
            }
        }
    }

    // Form state
    val firstNameState = MutableLiveData("")
    val firstNameError = MutableLiveData("")
    val lastNameState = MutableLiveData("")
    val lastNameError = MutableLiveData("")
    val emailState = MutableLiveData("")
    val emailError = MutableLiveData("")
    val passwordState = MutableLiveData("")
    val passwordError = MutableLiveData("")
    val confirmPasswordState = MutableLiveData("")
    val confirmPasswordError = MutableLiveData("")

    fun onTextChange(value: String, field: RegisterFields) {
        when (field) {
            RegisterFields.FIRSTNAME -> firstNameState.value = value

            RegisterFields.LASTNAME -> lastNameState.value = value

            RegisterFields.EMAIL -> emailState.value = value

            RegisterFields.PASSWORD -> passwordState.value = value

            RegisterFields.CONFIRMPASSWORD -> confirmPasswordState.value = value
        }
    }

    private fun validateForm(): Boolean {
        firstNameError.value = validateName(firstNameState.value)
        lastNameError.value = validateName(lastNameState.value)
        emailError.value = validateEmail(emailState.value)
        passwordError.value = validatePass(passwordState.value)
        confirmPasswordError.value = validateConfirmPass(confirmPasswordState.value)

        return firstNameError.value.isNullOrEmpty() && lastNameError.value.isNullOrEmpty() && emailError.value.isNullOrEmpty() && passwordError.value.isNullOrEmpty() && confirmPasswordError.value.isNullOrEmpty()
    }

    private fun validateName(value: String?): String? {
        val value = value?.trim()
        if(value.isNullOrEmpty()) return "This field is required!"
        if(!value.all {it.isLetter()}) return "Name may only contain letters"
        return if (value.length < 2) "Name must be longer then 2 characters" else null
    }

    private fun validateEmail(value: String?): String? {
        val value = value?.trim()
        if(value.isNullOrEmpty()) return "This field is required!"
        return if (!android.util.Patterns.EMAIL_ADDRESS.matcher(value).matches()) "Invalid e-mail address!" else null
    }

    private fun validatePass(value: String?): String? {
        if(value.isNullOrEmpty()) return "This field is required!"
        return if (value.length < 7) "Password must be 7 characters long" else null
    }

    private fun validateConfirmPass(value: String?): String? {
        if(value.isNullOrEmpty()) return "This field is required!"
        return if (value != passwordState.value) "Passwords do not match" else null
    }

    enum class RegisterFields {
        FIRSTNAME, LASTNAME, EMAIL, PASSWORD, CONFIRMPASSWORD
    }

}
