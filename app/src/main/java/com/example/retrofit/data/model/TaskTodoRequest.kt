package com.example.retrofit.data.model

data class TaskTodoRequest(
    val completed: Boolean,
    val title: String,
    val userId: Int
)