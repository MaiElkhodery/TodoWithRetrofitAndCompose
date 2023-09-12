package com.example.retrofit.todoscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.retrofit.R
import com.example.retrofit.ui.theme.ListBackgroundColor
import com.example.retrofit.ui.theme.TodoBasicColor
import com.example.retrofit.viewmodel.TodoEvent
import com.example.retrofit.viewmodel.TodoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoList(
    viewModel: TodoViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    ShowText(modifier = Modifier, text = stringResource(id = R.string.todo_app))
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = TodoBasicColor,
                    titleContentColor = Color.Black
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(TodoEvent.AddNewTodo)
                },
                containerColor = ListBackgroundColor,
                contentColor = Color.Black,
            ) {
                Icon(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12)),
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }
        },
        modifier = Modifier
            .padding(0.dp)
            .background(color = ListBackgroundColor)
    ) { padding ->


        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp),
            contentAlignment = Alignment.Center
        ) {

            if (state.todoList.isEmpty()) {
                ShowCircularProgress()
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                contentPadding = padding,
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                items(state.todoList) { item ->

                    if (state.isEditing) {
                        TodoDialog(state, item.id, viewModel::onEvent)
                    } else if (state.isAdding) {
                        TodoDialog(state, null, viewModel::onEvent)
                    }
                    TodoItem(item, viewModel::onEvent)

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