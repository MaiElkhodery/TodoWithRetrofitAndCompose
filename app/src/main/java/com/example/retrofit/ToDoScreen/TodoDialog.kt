package com.example.retrofit.todoscreen

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.retrofit.ui.theme.BlueBox
import com.example.retrofit.viewmodel.TodoEvent
import com.example.retrofit.viewmodel.TodoState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoDialog(
    state: TodoState,
    id : Int? = null,
    onEvent: (TodoEvent) -> Unit
) {
    val scrollState = rememberScrollState()
    Dialog(
        onDismissRequest = {
            onEvent(TodoEvent.CloseTodo)
        }
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(330.dp)
                .padding(20.dp)
                .clip(RoundedCornerShape(15))
                .scrollable(state = scrollState, orientation = Orientation.Vertical)
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 12.dp, vertical = 16.dp)
                        .weight(2f),
                    horizontalArrangement = Arrangement.Center
                ) {
                    TextField(
                        modifier = Modifier.weight(2f),
                        value = state.text,
                        onValueChange = { text ->
                            onEvent(TodoEvent.SetTodoText(text))
                        },
                        placeholder = {
                            Text(text = "ToDo")
                        }
                    )
                    Checkbox(
                        modifier = Modifier.weight(1f),
                        checked = state.isDone,
                        onCheckedChange = {
                            onEvent(TodoEvent.SetTodoChecking(it))
                        },
                        colors = CheckboxDefaults.colors(
                            checkedColor = BlueBox,
                            uncheckedColor = Color.Red
                        ),
                        enabled = true
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(12.dp)
                        .weight(1f),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(
                        onClick = {
                            if(state.isEditing){
                                onEvent(TodoEvent.UpdateTodo(id!!,state.text,state.isDone))
                                onEvent(TodoEvent.CloseTodo)
                            }else if(state.isAdding){
                                onEvent(TodoEvent.CreateTodo)
                                onEvent(TodoEvent.CloseTodo)
                            }
                        }
                    ) {
                        Text(text = "Save")
                    }
                    TextButton(
                        onClick = {
                            onEvent(TodoEvent.CloseTodo)
                        }
                    ) {
                        Text(text = "Cancel")
                    }
                }
            }

        }
    }
}

@Composable
@Preview
fun Preview2() {
    TodoDialog(
        state = TodoState(
            emptyList(),
            true,
            false,
            "Hello",
            false
        ),
        id=0
    ) {

    }
}