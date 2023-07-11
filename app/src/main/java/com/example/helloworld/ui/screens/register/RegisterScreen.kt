package com.example.helloworld.ui.screens.register

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.example.helloworld.ui.components.Custombutton

@Composable
fun RegisterScreen() {
    Surface(
        color= MaterialTheme.colorScheme.background,
        modifier = Modifier
        .fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            RegisterTitle()

            CustomTextField(
                label = "Email",
            )

            CustomTextField(
                label = "Password",
                modifier = Modifier
                    .offset(y = 15.dp)
            )

            Custombutton(
                btnText = "Register",
                onClick = {
                    Log.d("app_logs", "Register clicked")
                },
                yOffset = 30.dp
            )
        }
    }
}


@Composable
fun CustomTextField(
    label: String,
    modifier: Modifier = Modifier
) {
    val text = remember { mutableStateOf(TextFieldValue("")) }

    TextField(
        value = text.value,
        onValueChange = {
            text.value = it
        },
        label = { Text(text = label) },
        placeholder = { Text(text = "Enter your $label") },
        modifier = modifier
    )
}

@Composable
fun RegisterTitle() {
    Text(
        text = "Register to access the platform",
        color = MaterialTheme.colorScheme.primary,
        textAlign = TextAlign.Center,
        fontSize = 5.em,
        lineHeight = 1.em,
        fontWeight = FontWeight.Normal,
        modifier = Modifier
            .padding(vertical = 20.dp, horizontal = 15.dp)
    )

}