package com.example.retrofit.todoscreen

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.retrofit.R
import com.example.retrofit.ui.theme.ListBackgroundColor
import com.example.retrofit.viewmodel.TodoEvent
import com.example.retrofit.viewmodel.TodoState

@Composable
fun TodoDialog(
    state: TodoState,
    id: Int? = null,
    onEvent: (TodoEvent) -> Unit
) {
    val scrollState = rememberScrollState()
    Dialog(
        onDismissRequest = {
            onEvent(TodoEvent.CloseTodo)
        }
    ) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .height(330.dp)
                .padding(12.dp)
                .clip(RoundedCornerShape(9))
                .scrollable(state = scrollState, orientation = Orientation.Vertical),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            colors = CardDefaults.cardColors(ListBackgroundColor)
        ) {
            Column(
                modifier = Modifier
                    .padding(22.dp)
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(3f),
                ) {
                    Checkbox(
                        modifier = Modifier
                            .weight(1f),
                        checked = state.isDone,
                        onCheckedChange = {
                            onEvent(TodoEvent.SetTodoChecking(it))
                        },
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color.Black,
                            uncheckedColor = Color.Gray
                        ),
                        enabled = true
                    )
                    OutlinedTextField(
                        modifier = Modifier
                            .padding(vertical = 10.dp)
                            .fillMaxHeight()
                            .weight(5f),
                        value = state.text,
                        onValueChange = { text ->
                            onEvent(TodoEvent.SetTodoText(text))
                        },
                        placeholder = {
                            Text(text = "ToDo")
                        },
                        textStyle = TextStyle(
                            fontSize = 18.sp,
                            textAlign = TextAlign.Start,
                            fontFamily = FontFamily(
                                Font(
                                    R.font.akaya_telivigala
                                )
                            ),
                            fontWeight = FontWeight.Medium
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                        .weight(1f)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(
                        onClick = {
                            if (state.isEditing) {
                                onEvent(TodoEvent.UpdateTodo(id!!, state.text, state.isDone))
                                onEvent(TodoEvent.CloseTodo)
                            } else if (state.isAdding) {
                                onEvent(TodoEvent.CreateTodo)
                                onEvent(TodoEvent.CloseTodo)
                            }
                        }
                    ) {
                        ShowTextButton(text = "Save")
                    }
                    TextButton(
                        onClick = {
                            onEvent(TodoEvent.CloseTodo)
                        }
                    ) {
                        ShowTextButton(text = "Cancel")
                    }
                }
            }

        }
    }
}

@Composable
fun ShowTextButton(text: String) {
    Text(
        text = text,
        fontSize = 18.sp,
        textAlign = TextAlign.End,
        fontFamily = FontFamily(
            Font(
                R.font.akaya_telivigala
            )
        ),
        fontWeight = FontWeight.Medium,
        color = Color.Black
    )
}

@Composable
@Preview
fun Preview2() {
    TodoDialog(
        state = TodoState(
            emptyList(),
            isAdding = true,
            isEditing = false,
            "Hello",
            false
        ),
        id = 0
    ) {

    }
}