package com.example.retrofit.model

data class TodoResponse(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)