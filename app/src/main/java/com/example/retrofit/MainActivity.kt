package com.example.retrofit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.retrofit.ToDoScreen.TodoList
import com.example.retrofit.ui.theme.RetrofitTheme
import com.example.retrofit.viewmodel.TodoViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val viewModel : TodoViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            viewModel.getTodoList()
        }
        setContent {
            RetrofitTheme {
                TodoList(list = viewModel._listOfTodo.value)
            }

        }
    }
}
