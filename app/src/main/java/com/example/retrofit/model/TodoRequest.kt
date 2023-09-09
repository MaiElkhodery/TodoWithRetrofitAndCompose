package com.example.retrofit.model

data class TodoRequest(
    val completed: Boolean,
    val title: String,
    val userId: Int
)