package com.example.retrofit.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit.ToDoScreen.TodoResponse
import com.example.retrofit.api.RetrofitInstance
import kotlinx.coroutines.launch

class TodoViewModel:ViewModel() {

    var _listOfTodo :MutableState<List<TodoResponse>> = mutableStateOf(emptyList())

    suspend fun getTodoList(){
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getTodo("key")
                if(response.isSuccessful){
                    _listOfTodo.value = response.body()!!
                }else{
                    // not success
                }
            }catch (e:Exception){
                Log.d("NetworkError",e.message.toString())
            }
        }
    }
}