package com.example.retrofit.ToDoScreen

data class TodoResponse(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)