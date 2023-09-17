package com.example.retrofit.presentation.viewmodel

import com.example.retrofit.data.model.TaskTodoResponse

data class TodoState(
    var todoList: MutableList<TaskTodoResponse> = mutableListOf(),
    val isAdding: Boolean = false,
    val isEditing: Boolean = false,
    var text: String = "",
    var isDone: Boolean = false
)