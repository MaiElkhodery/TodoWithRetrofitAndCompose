package com.example.retrofit

import android.content.Context
import android.widget.Toast

fun showToast(msg: String, context: Context) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}

fun showErrorMsg(status: Int, context: Context) {
    when (status) {
        400 -> showToast("Client Error",context)
        500 -> showToast("Server Error",context)
        else -> showToast("$status Error",context)
    }
}
