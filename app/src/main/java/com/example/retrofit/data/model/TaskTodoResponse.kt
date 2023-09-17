package com.example.retrofit.data.model

data class TaskTodoResponse (
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)