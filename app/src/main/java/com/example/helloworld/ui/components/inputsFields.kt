package com.example.helloworld.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun CustomTextField(
    label: String,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier = Modifier
) {
    val text = remember { mutableStateOf(TextFieldValue("")) }

    TextField(
        value = text.value,
        onValueChange = {
            text.value = it
        },
        keyboardOptions = keyboardOptions,
        label = { Text(text = label) },
        placeholder = { Text(text = "Enter your $label") },
        modifier = modifier
    )
}

@Composable
fun PasswordTextField(
    label: String,
    modifier: Modifier = Modifier
) {
    val password = remember { mutableStateOf(TextFieldValue("")) }
    val showPassword = remember { mutableStateOf(value = false) }

    TextField(
        modifier = modifier
            .fillMaxWidth(),
        value = password.value,
        onValueChange = {
            password.value = it
        },
        label = {
            Text(text = label)
        },
        placeholder = { Text(text = "Type password here") },
        shape = RoundedCornerShape(percent = 20),
        visualTransformation = if (showPassword.value) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            if (showPassword.value) {
                IconButton(onClick = { showPassword.value = false }) {
                    Icon(
                        imageVector = Icons.Filled.Visibility,
                        contentDescription = "hide_password"
                    )
                }
            } else {
                IconButton(
                    onClick = { showPassword.value = true }) {
                    Icon(
                        imageVector = Icons.Filled.VisibilityOff,
                        contentDescription = "hide_password"
                    )
                }
            }
        }
    )
}