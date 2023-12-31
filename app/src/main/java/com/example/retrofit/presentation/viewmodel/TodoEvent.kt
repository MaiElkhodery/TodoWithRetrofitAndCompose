package com.example.retrofit.presentation.viewmodel

import com.example.retrofit.data.model.TaskTodoResponse

sealed interface TodoEvent {
    object CloseTodo : TodoEvent
    object CreateTodo : TodoEvent
    data class UpdateTodo(
        val id: Int,
        val text: String,
        val isDone: Boolean
    ) : TodoEvent

    data class DeleteTodo(
        val task: TaskTodoResponse
    ) : TodoEvent

    data class SetTodoText(
        val text: String
    ) : TodoEvent
    data class SetTodoChecking(
        val isChecked: Boolean
    ) : TodoEvent
    object AddNewTodo : TodoEvent
    object EditTodo : TodoEvent


}