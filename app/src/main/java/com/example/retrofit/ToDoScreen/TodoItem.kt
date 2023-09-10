package com.example.retrofit.todoscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.retrofit.R
import com.example.retrofit.viewmodel.TodoEvent
import com.example.retrofit.model.TodoResponse
import com.example.retrofit.ui.theme.BlueBox
import com.example.retrofit.ui.theme.LightBlue
import com.example.retrofit.viewmodel.TodoViewModel

@Composable
fun TodoItem(
    item: TodoResponse,
    viewModel: TodoViewModel = hiltViewModel()
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                viewModel.onEvent(TodoEvent.EditTodo)
            },
        colors = CardDefaults.cardColors(LightBlue)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .weight(2f)
                    .padding(8.dp),
                text = item.title,
                fontSize = 16.sp,
                textAlign = TextAlign.Start
            )

            Checkbox(
                checked = item.completed,
                onCheckedChange = {},
                colors = CheckboxDefaults.colors(
                    checkedColor = BlueBox,
                    uncheckedColor = Color.Red),
            )
            Icon(
                modifier = Modifier.clickable {
                      viewModel.onEvent(TodoEvent.DeleteTodo(item.id))
                },
                painter = painterResource(id = R.drawable.delete),
                contentDescription = null
            )
        }
    }
}

@Composable
@Preview
fun Preview3() {
    TodoItem(item = TodoResponse(true,2,"this is my todo",1))
}
