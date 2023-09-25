package com.example.retrofit.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.retrofit.presentation.Homescreen.TodoList
import com.example.retrofit.presentation.loginscreen.LoginScreen
import com.example.retrofit.presentation.signupscreen.SignupScreen

@Composable
fun Navigation(navController : NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ){
        composable(
            route = Screen.HomeScreen.route
        ){
            TodoList()
        }
        composable(
            route = Screen.SignupScreen.route
        ){
            SignupScreen()
        }
        composable(
            route = Screen.LoginScreen.route
        ){
            LoginScreen()
        }
    }
}