package com.example.retrofit.api

import com.example.retrofit.ToDoScreen.TodoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TodoApi {
    @GET("/todos")
    suspend fun getTodo(@Query("key") key :String):Response<List<TodoResponse>>
}