package com.example.retrofit.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit.data.api.ApiHandler
import com.example.retrofit.data.api.NetworkResult
import com.example.retrofit.data.model.TaskTodoRequest
import com.example.retrofit.data.model.TaskTodoResponse
import com.example.retrofit.data.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val repo: TodoRepository
) : ViewModel(), ApiHandler {

    private val _state: MutableStateFlow<TodoState> = MutableStateFlow(TodoState())
    val state = _state.asStateFlow()

    private suspend fun getTodoList() {
        viewModelScope.launch {
            val response = handleApi { repo.getTodoList() }
            when (response) {
                is NetworkResult.Error -> {
                    Log.d("ResponseMsgG", "Error: ${response.errorMsg}")
                }

                is NetworkResult.Exception -> {
                    Log.d("ResponseMsgG", "Exception: ${response.e.message}")
                }

                is NetworkResult.Success -> {
                    Log.d("ResponseMsgG", "List size: ${response.data.size}")
                    _state.update {
                        it.copy(
                            todoList = response.data.toMutableList()
                        )
                    }
                    Log.d("IsListIsEmpty", state.value.todoList.isEmpty().toString())
                }
            }
        }
    }

    private suspend fun updateTodoTask(task: TaskTodoResponse) {
        viewModelScope.launch {
            val response = handleApi {
                repo.updateTask(
                    task.id, TaskTodoRequest(
                        title = task.title, completed = task.completed, userId = task.userId
                    )
                )
            }
            when (response) {
                is NetworkResult.Error -> {
                    Log.d("ResponseMsg", "Error: ${response.errorMsg}")
                }

                is NetworkResult.Exception -> {
                    Log.d("ResponseMsg", "Exception: ${response.e.message}")
                }

                is NetworkResult.Success -> {
                    Log.d("ResponseMsg", "Task: ${response.data}")

                   getTodoList()
                }
            }
        }

    }

    private suspend fun createTask(task: TaskTodoRequest) {
        viewModelScope.launch {
            val response = handleApi {
                repo.createTask(task)
            }
            when (response) {
                is NetworkResult.Error -> {
                    Log.d("ResponseMsg", "Error: ${response.errorMsg}")
                }

                is NetworkResult.Exception -> {
                    Log.d("ResponseMsg", "Exception: ${response.e.message}")
                }

                is NetworkResult.Success -> {
                    Log.d("ResponseMsg", "Task: ${response.data}")
                    getTodoList()
                }
            }
        }
    }

    private suspend fun deleteTask(task: TaskTodoResponse) {
        viewModelScope.launch {
            val response = handleApi {
                repo.deleteTask(task.id)
            }
            when (response) {
                is NetworkResult.Error -> {
                    Log.d("ResponseMsg", "Error: ${response.errorMsg}")
                }

                is NetworkResult.Exception -> {
                    Log.d("ResponseMsg", "Exception: ${response.e.message}")
                }

                is NetworkResult.Success -> {
                    Log.d("ResponseMsg", "Task: ${response.data}")
                    val index = _state.value.todoList.indexOf(task)
                    _state.value.todoList.removeAt(index)
                    _state.update {
                        it.copy(
                            todoList = _state.value.todoList
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: TodoEvent) {
        when (event) {

            is TodoEvent.DeleteTodo -> {
                viewModelScope.launch {
                    deleteTask(event.task)
                }
            }

            TodoEvent.CloseTodo -> {
                viewModelScope.launch {
                    _state.update {
                        it.copy(
                            isEditing = false, isAdding = false, text = "", isDone = false
                        )
                    }
                }
            }

            is TodoEvent.CreateTodo -> {
                viewModelScope.launch {
                    val text = _state.value.text
                    val done = _state.value.isDone

                    createTask(
                        TaskTodoRequest(
                            completed = done, title = text, userId = repo.USERID
                        )
                    )
                }
            }

            is TodoEvent.UpdateTodo -> {
                viewModelScope.launch {
                    updateTodoTask(
                        TaskTodoResponse(
                            title = event.text,
                            completed = event.isDone,
                            id = event.id,
                            userId = repo.USERID
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