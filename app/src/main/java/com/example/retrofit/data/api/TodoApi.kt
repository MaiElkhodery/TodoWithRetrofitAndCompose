package com.example.retrofit.data.api

import com.example.retrofit.data.model.TaskTodoResponse
import com.example.retrofit.data.model.TaskTodoRequest
import com.example.retrofit.data.model.TodoListResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface TodoApi {
    @GET(TODO_URL)
    suspend fun getTodoList(
        @Query("userId") userId : Int
    ):Response<List<TaskTodoResponse>>

    @PUT("$TODO_URL/{id}")
    suspend fun updateTask(
        @Path("id") resourceId:Int,
        @Body todoItem: TaskTodoRequest
    ):Response<TaskTodoResponse>

    @POST(TODO_URL)
    suspend fun createTodo(
        @Body todoItem : TaskTodoRequest
    ):Response<TaskTodoResponse>

    @DELETE("$TODO_URL/{id}")
    suspend fun deleteTodo(@Path("id") id:Int):Response<TaskTodoResponse>
}