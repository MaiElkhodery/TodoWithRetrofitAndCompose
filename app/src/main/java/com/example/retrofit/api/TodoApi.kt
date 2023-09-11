package com.example.retrofit.api

import com.example.retrofit.model.TodoRequest
import com.example.retrofit.model.TodoResponse
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
        @Query("key") key : String,
        @Query("userId") userId : Int
    ):Response<List<TodoResponse>>

    @GET("TODO_URL/{id}")
    suspend fun getTodo(@Path("id") id : Int):Response<TodoResponse>
    @PUT("$TODO_URL/{id}")
    suspend fun updateTodo(
        @Path("id") resourceId:Int,
        @Body todoItem: TodoResponse
    ):Response<TodoResponse>

    @POST(TODO_URL)
    suspend fun createTodo(
        @Body todoItem : TodoRequest
    ):Response<TodoResponse>

    @DELETE("$TODO_URL/{id}")
    suspend fun deleteTodo(@Path("id") id:Int)
}