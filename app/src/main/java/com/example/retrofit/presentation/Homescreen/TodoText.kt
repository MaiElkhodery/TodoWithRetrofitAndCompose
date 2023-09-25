package com.example.retrofit.presentation.Homescreen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.retrofit.R

@Composable
fun ShowText(
    modifier: Modifier,
    text: String
) {

    Text(
        modifier = modifier,
        text = text,
        fontSize = 18.sp,
        textAlign = TextAlign.Start,
        fontFamily = FontFamily(
            Font(
                R.font.akaya_telivigala
            )
        ),
        fontWeight = FontWeight.Medium
    )

}