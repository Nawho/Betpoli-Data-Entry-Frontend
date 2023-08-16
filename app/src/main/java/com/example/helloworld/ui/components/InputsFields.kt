package com.example.helloworld.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun EmailTextField(
    label: String,
    errorState: MutableState<Boolean>,
    modifier: Modifier = Modifier
) {
    val email = remember { mutableStateOf(TextFieldValue("")) }
    val firstInput = remember { mutableStateOf(true) }
    val focusManager = LocalFocusManager.current

    Column() {
        TextField(
            value = email.value,
            onValueChange = {
                email.value = it
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Email,
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    firstInput.value = false
                    focusManager.moveFocus(FocusDirection.Down)
                }
            ),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer
            ),
            label = { Text(text = label) },
            singleLine = true,
            placeholder = { Text(text = "Ingrese su $label") },
            modifier = modifier,
        )

        if (!firstInput.value) {
            if (!isValidEmail(email.value.text)) {
                errorState.value = true
                Log.d("app_logs", "Email error")
                Text(
                    text = "Email is not valid",
                    color = MaterialTheme.colorScheme.error,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .offset(y = 5.dp)
                )
            } else {
                Log.d("app_logs", "Email correct")
                errorState.value = false
            }
        }
    }
}



@Composable
fun PasswordTextField(
    label: String,
    errorState: MutableState<Boolean>,
    modifier: Modifier = Modifier
) {
    val password = remember { mutableStateOf(TextFieldValue("")) }
    val showPassword = remember { mutableStateOf(value = false) }
    val errorShowState = remember { mutableStateOf(0) }
    val focusManager = LocalFocusManager.current

    Column() {
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
            singleLine = true,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer
            ),
            placeholder = { Text(text = "Ingrese su contraseña") },
            shape = RoundedCornerShape(8.dp),
            visualTransformation = if (showPassword.value) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done,
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    errorShowState.value = 1
                    focusManager.clearFocus()
                }
            ),
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
            },

        )

        if (password.value.text.isBlank() && errorShowState.value == 1) {
            errorState.value = true
            Log.d("app_logs", "Password error")

            Text(
                text = "Password can not be empty.",
                color = MaterialTheme.colorScheme.error,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .offset(y = 5.dp)
            )
        } else if(errorShowState.value == 1) {
            Log.d("app_logs", "Password correct")
            errorState.value = false
        }
    }
}

fun isValidEmail(email: String): Boolean {
    val emailRegex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()
    return email.matches(emailRegex)
}

fun isValidPassword(password: String): Boolean {
    return password.isNotBlank().and(password.isNotEmpty())
}