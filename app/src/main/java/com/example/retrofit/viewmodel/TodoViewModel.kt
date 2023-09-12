package com.example.retrofit.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit.api.ApiHandler
import com.example.retrofit.api.NetworkResult
import com.example.retrofit.api.RetrofitInstance
import com.example.retrofit.model.TodoRequest
import com.example.retrofit.model.TodoResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TodoViewModel : ViewModel(), ApiHandler {

    private val _state: MutableStateFlow<TodoState> = MutableStateFlow(TodoState())
    val state = _state.asStateFlow()
    private val API_INSTANCE = RetrofitInstance.api
    private val USER_ID = 2


    suspend fun getTodoList() {
        viewModelScope.launch {
            val result = handleApi {
                API_INSTANCE.getTodoList("key", USER_ID)
            }
            when (result) {
                is NetworkResult.Error -> {
                    Log.d("ResponseResult", "${result.code} ${result.errorMsg.toString()}")
                }

                is NetworkResult.Exception -> {
                    Log.d("ResponseResult", "${result.e.message}")
                }

                is NetworkResult.Success -> {
                    Log.d("ResponseResult", "${result.code}")
                    viewModelScope.launch {
                        _state.update {
                            it.copy(
                                todoList = result.data
                            )
                        }
                    }
                }
            }
        }
    }

    private suspend fun updateTodoItem(item: TodoResponse) {
        viewModelScope.launch {
            val result = handleApi {
                API_INSTANCE
                    .updateTodo(
                        todoItem = item,
                        resourceId = item.id
                    )
            }
            when (result) {
                is NetworkResult.Error -> {
                    Log.d("ResponseResult", "${result.code} ${result.errorMsg.toString()}")
                }

                is NetworkResult.Exception -> {
                    Log.d("ResponseResult", "${result.e.message}")
                }

                is NetworkResult.Success -> {
                    getTodoList()
                    Log.d("ResponseResult", "${result.data}")

                }
            }
        }
    }

    private suspend fun createNewItem(item: TodoRequest) {
        viewModelScope.launch {
            val result = handleApi {
                API_INSTANCE.createTodo(item)
            }
            when (result) {
                is NetworkResult.Error -> {
                    Log.d("ResponseResult", "${result.code} ${result.errorMsg.toString()}")
                }

                is NetworkResult.Exception -> {
                    Log.d("ResponseResult", "${result.e.message}")
                }

                is NetworkResult.Success -> {
                    getTodoList()
                    Log.d("ResponseResult", "${result.data}")

                }
            }
        }
    }

    private suspend fun deleteItem(id: Int) {
        viewModelScope.launch {
            API_INSTANCE.deleteTodo(id)
            getTodoList()
        }
    }

    fun onEvent(event: TodoEvent) {
        when (event) {

            is TodoEvent.DeleteTodo -> {
                viewModelScope.launch {
                    deleteItem(event.id)
                }
            }

            TodoEvent.CloseTodo -> {
                viewModelScope.launch {
                    _state.update {
                        it.copy(
                            isEditing = false,
                            isAdding = false,
                            text = "",
                            isDone = false
                        )
                    }
                }
            }

            is TodoEvent.CreateTodo -> {
                viewModelScope.launch {
                    val text = _state.value.text
                    val done = _state.value.isDone

                    createNewItem(
                        TodoRequest(
                            completed = done,
                            title = text,
                            userId = USER_ID
                        )
                    )
                }
            }

            is TodoEvent.UpdateTodo -> {
                viewModelScope.launch {
                    updateTodoItem(
                        TodoResponse(
                            id = event.id,
                            completed = event.isDone,
                            title = event.text,
                            userId = USER_ID
                        )
                    )
                }
            }


            is TodoEvent.SetTodoChecking -> {
                viewModelScope.launch {
                    _state.update {
                        it.copy(
                            isDone = event.isChecked
                        )
                    }
                }
            }

            is TodoEvent.SetTodoText -> {
                viewModelScope.launch {
                    _state.update {
                        it.copy(
                            text = event.text
                        )
                    }
                }
            }

            TodoEvent.AddNewTodo -> {
                viewModelScope.launch {
                    _state.update {
                        it.copy(
                            isAdding = true
                        )
                    }
                }
            }

            TodoEvent.EditTodo -> {
                viewModelScope.launch {
                    _state.update {
                        it.copy(
                            isEditing = true
                        )
                    }
                }
            }
        }
    }
    init {
        viewModelScope.launch {
            getTodoList()
        }
    }

}