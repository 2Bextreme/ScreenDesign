package com.example.screendesign.data

data class LoginFormState (
    val usernameError: String? = null,
    val passwordError: String? = null,
    val isDataValid: Boolean = false
)
