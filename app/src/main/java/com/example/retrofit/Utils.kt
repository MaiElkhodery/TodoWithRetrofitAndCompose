package com.example.retrofit

import android.accounts.NetworkErrorException
import android.content.Context
import android.widget.Toast
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.concurrent.TimeoutException
import javax.inject.Inject

class HelperClass @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun showToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
    fun handleApiError(error: Throwable) {
        val errorMessage = when (error) {
            is NetworkErrorException -> "Network error occurred"
            is TimeoutException -> "Request timed out"
            else -> "An error occurred"
        }

        showToast(errorMessage)
    }
}