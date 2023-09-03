package com.example.retrofit.ToDoScreen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.retrofit.ui.theme.BlueBox
import com.example.retrofit.ui.theme.LightBlue

@Composable
fun TodoItem(item: TodoResponse) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
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
                onCheckedChange = {

                },
                colors = CheckboxDefaults.colors(
                    checkedColor = BlueBox,
                    uncheckedColor = Color.Red
                ),

                )

        }
    }
}
