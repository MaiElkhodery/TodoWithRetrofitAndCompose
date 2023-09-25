package com.example.retrofit.presentation

sealed class Screen(
    val route: String
) {
    object HomeScreen : Screen("home")
    object SignupScreen : Screen("signup")
    object LoginScreen : Screen("login")
}
