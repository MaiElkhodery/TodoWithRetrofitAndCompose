package com.example.retrofit.api

sealed class NetworkResult<out T : Any> {
    data class Success<out T : Any>(val code: Int,val data :T): NetworkResult<T>()
    data class Error<out T :Any>(val code :Int ,val errorMsg : T?): NetworkResult<T>()
    data class Exception(val e : Throwable): NetworkResult<Nothing>()
}
