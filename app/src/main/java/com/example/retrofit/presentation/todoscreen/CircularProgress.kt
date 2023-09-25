package com.example.retrofit.presentation.todoscreen

import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ShowCircularProgress(){
    CircularProgressIndicator(
        modifier = Modifier
            .width(64.dp),
        color = Color.Gray,
        strokeWidth = 5.dp
    )
}
@Composable
@Preview
fun Preview5() {
    ShowCircularProgress()
}