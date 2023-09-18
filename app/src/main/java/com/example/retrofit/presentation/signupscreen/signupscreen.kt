package com.example.retrofit.presentation.signupscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.retrofit.R


@Composable
fun SignupScreen() {

    Column(
        modifier = Modifier.background(color = Color.White)

    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.drawable.todo_img),
                contentDescription = null
            )
        }
        Card(
            modifier = Modifier
                .padding(horizontal = 20.dp),
            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Black
            )
        ) {


            Column(
                modifier = Modifier.padding(horizontal = 26.dp),
                horizontalAlignment = Alignment.Start
            ) {
                VerticalSpacer()
                ShowAuthText(authText = stringResource(id = R.string.signup))
                VerticalSpacer()
                ShowInputField(label = "User Name")
                VerticalSpacer()
                ShowInputField(label = "Email")
                VerticalSpacer()
                ShowInputField(label = "Password")
                VerticalSpacer()
                ShowAuthButton(text = "Sign up")
                VerticalSpacer()
                ShowAuthBottomText(
                    text1 = "Already have an account",
                    text2 = "Login"
                )
                VerticalSpacer()
            }
        }


    }

}

@Composable
@Preview
fun PreviewS() {
    SignupScreen()
}