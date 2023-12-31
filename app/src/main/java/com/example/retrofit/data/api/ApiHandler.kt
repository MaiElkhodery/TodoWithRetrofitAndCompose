package com.example.retrofit.data.api

import retrofit2.HttpException
import retrofit2.Response

interface ApiHandler {
    suspend fun < T : Any> handleApi(
        execute: suspend () -> Response<T>
    ): NetworkResult<T> {
        return try {
            val response = execute()

            if (response.isSuccessful) {
                NetworkResult.Success(response.code(), response.body()!!)
            } else {
                NetworkResult.Error(response.code(), response.errorBody().toString() as T?)
            }
        } catch (e: HttpException) {
            NetworkResult.Error(e.code(), e.message() as T?)
        } catch (e: Throwable) {
            NetworkResult.Exception(e)
        }
    }

}