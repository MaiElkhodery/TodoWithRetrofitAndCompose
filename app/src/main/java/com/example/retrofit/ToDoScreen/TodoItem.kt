package com.example.retrofit.todoscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.retrofit.R
import com.example.retrofit.model.TodoResponse
import com.example.retrofit.ui.theme.CardColor
import com.example.retrofit.viewmodel.TodoEvent

@Composable
fun TodoItem(
    item: TodoResponse,
    onEvent: (TodoEvent) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp)
            .clickable {
                onEvent(TodoEvent.SetTodoText(item.title))
                onEvent(TodoEvent.SetTodoChecking(item.completed))
                onEvent(TodoEvent.EditTodo)
            }
            .clip(RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(CardColor)
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            ShowText(
                modifier = Modifier.weight(5f),
                text = item.title
            )

            Checkbox(
                modifier = Modifier
                    .weight(1f),
                checked = item.completed,
                onCheckedChange = {
                },
                colors = CheckboxDefaults.colors(
                    checkedColor = Color.Black,
                    uncheckedColor = Color.Gray
                ),
                enabled = false
            )
            Icon(
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        onEvent(TodoEvent.DeleteTodo(item.id))
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
    TodoItem(item = TodoResponse(true, 2, "this is my todo", 1)) {}
}
