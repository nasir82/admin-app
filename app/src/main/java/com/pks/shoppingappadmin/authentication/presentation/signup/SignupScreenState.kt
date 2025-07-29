package com.pks.shoppingappadmin.authentication.presentation.signup

data class SignupScreenState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val userData: String? = null
)