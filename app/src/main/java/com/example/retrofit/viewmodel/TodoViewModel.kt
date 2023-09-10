package com.example.retrofit.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit.api.RetrofitInstance
import com.example.retrofit.model.TodoRequest
import com.example.retrofit.model.TodoResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TodoViewModel: ViewModel() {

    private val _state: MutableStateFlow<TodoState> = MutableStateFlow(TodoState())
    val state = _state.asStateFlow()
    private val API_INSTANCE = RetrofitInstance.api
    private val USER_ID = 2


    private suspend fun getTodoList() {
        viewModelScope.launch {
            try {
                val response = API_INSTANCE.getTodoList(USER_ID)
                if (response.isSuccessful) {
                    _state.value.todoList= response.body()!!
                } else {
                    //showErrorMsg(response.code(), LocalContext.current)
                }
            } catch (e: Exception) {
                Log.d("NetworkError", e.message.toString())
            }
        }
    }

    private suspend fun updateTodoItem(item: TodoResponse) {
        viewModelScope.launch {
            try {
                val response = API_INSTANCE
                    .updateTodo(
                        todoItem = item,
                        resourceId = item.id
                    )
                if (response.isSuccessful) {
                    getTodoList()
                }

            } catch (e: Exception) {
                //not success
            }
        }
    }

    private suspend fun createNewItem(item: TodoRequest) {
        viewModelScope.launch {
            try {
                val response = API_INSTANCE
                    .createTodo(item)
                if (response.isSuccessful) {
                    getTodoList()
                }

            } catch (e: Exception) {
                //not success
            }
        }
    }

    private suspend fun deleteItem(id: Int) {
        viewModelScope.launch {
            try {
                val response = API_INSTANCE
                    .deleteTodo(id)

                getTodoList()

            } catch (e: Exception) {
                //not success
            }
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
                    val text = _state.value.text
                    val done = _state.value.isDone
                    updateTodoItem(
                        TodoResponse(
                            id = event.id,
                            completed = done,
                            title = text,
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