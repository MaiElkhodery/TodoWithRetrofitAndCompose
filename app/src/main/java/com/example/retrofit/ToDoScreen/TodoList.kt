package com.example.retrofit.todoscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.retrofit.viewmodel.TodoEvent
import com.example.retrofit.viewmodel.TodoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoList(
    viewModel: TodoViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(TodoEvent.AddNewTodo)
                }
            )
            {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }
        },
        modifier = Modifier.padding(16.dp)
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = padding,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(state.todoList) { item ->
                TodoItem(item, viewModel)
                if(state.isEditing){
                    state.text = item.title
                    state.isDone = item.completed
                    TodoDialog(state,item.id,viewModel::onEvent)
                }else if(state.isAdding){
                    TodoDialog(state,null,viewModel::onEvent)
                }
            }
        }
    }

}

@Composable
@Preview
fun Preview() {
    TodoList()
}