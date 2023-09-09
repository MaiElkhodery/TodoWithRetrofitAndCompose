package com.example.retrofit

import com.example.retrofit.model.TodoResponse

data class TodoState(
    var todoList: List<TodoResponse> = emptyList(),
    val isAdding: Boolean = false,
    val isEditing: Boolean = false,
    var text: String = "",
    var isDone: Boolean = false
)