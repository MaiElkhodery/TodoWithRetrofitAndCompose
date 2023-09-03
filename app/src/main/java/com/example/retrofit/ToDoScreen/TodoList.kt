package com.example.retrofit.ToDoScreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TodoList(list : List<TodoResponse>){
    LazyColumn (
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ){
        items(list){item->
            TodoItem(item)
        }
    }
}
@Composable
@Preview
fun Preview(){
    TodoList(list = listOf(
        TodoResponse(
        title = "hello",
        completed = true,
        id =1,
        userId = 0
    )
    ))
}