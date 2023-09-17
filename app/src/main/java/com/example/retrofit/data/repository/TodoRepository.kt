package com.example.retrofit.data.repository

import com.example.retrofit.data.api.RetrofitInstance
import com.example.retrofit.data.model.TaskTodoRequest
import com.example.retrofit.data.model.TaskTodoResponse
import retrofit2.Response


class TodoRepository {
    val USERID = 2
    suspend fun getTodoList(): Response<List<TaskTodoResponse>> {
        return RetrofitInstance.api.getTodoList(USERID)
    }

    suspend fun updateTask(
        id: Int,
        task: TaskTodoRequest
    ): Response<TaskTodoResponse> {
        return RetrofitInstance.api.updateTask(id, task)
    }

    suspend fun createTask(
        task: TaskTodoRequest
    ): Response<TaskTodoResponse> {
        return RetrofitInstance.api.createTodo(task)
    }

    suspend fun deleteTask(
        id: Int
    ): Response<TaskTodoResponse> {
        return RetrofitInstance.api.deleteTodo(id)
    }

}