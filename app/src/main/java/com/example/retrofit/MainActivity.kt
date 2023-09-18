package com.example.retrofit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.retrofit.presentation.Homescreen.TodoList
import com.example.retrofit.presentation.Navigation
import com.example.retrofit.ui.theme.RetrofitTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RetrofitTheme {
                val navController = rememberNavController()
                Navigation(navController = navController)

                TodoList()
            }

        }
    }
}
