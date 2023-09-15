package com.example.betpoli.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.betpoli.R

@Composable
fun EmailTextField(
    errorState: MutableState<Boolean>,
    emailValue: MutableState<TextFieldValue>,
    modifier: Modifier = Modifier
) {
    val firstInput = remember { mutableStateOf(true) }
    val isInitialComposition = remember { mutableStateOf(true) }
    val focusManager = LocalFocusManager.current

    LaunchedEffect(isInitialComposition) {
        isInitialComposition.value = false
    }

    Column {
        TextField(
            label = { Text(text = stringResource(R.string.email_label)) },
            placeholder = { Text(text = stringResource(R.string.email_placeholder)) },
            value = emailValue.value,
            onValueChange = { emailValue.value = it },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
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
            shape = RoundedCornerShape(8.dp),
            modifier = modifier
                .background(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.secondaryContainer,
                )
                .onFocusChanged { if (!it.isFocused && !isInitialComposition.value) {
                    firstInput.value = false
                }},
        )

        if (!firstInput.value) {
            if (!isValidEmail(emailValue.value.text)) {
                errorState.value = true
                Text(
                    text = stringResource(R.string.email_invalid),
                    color = MaterialTheme.colorScheme.error,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .offset(y = 5.dp)
                )
            } else {
                errorState.value = false
            }
        }
    }
}



@Composable
fun PasswordTextField(
    errorState: MutableState<Boolean>,
    passwordValue: MutableState<TextFieldValue>,
    modifier: Modifier = Modifier
) {
    val showPassword = remember { mutableStateOf(value = false) }
    val firstInput = remember { mutableStateOf(true) }
    val focusManager = LocalFocusManager.current

    Column(
        Modifier.background(color = Color.Transparent)
    ) {
        TextField(
            label = { Text(text = stringResource(R.string.password_label)) },
            placeholder = { Text(text = stringResource(R.string.password_placeholder)) },
            value = passwordValue.value,
            onValueChange = {passwordValue.value = it },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            visualTransformation = if (showPassword.value) VisualTransformation.None
                else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { showPassword.value = !showPassword.value }) {
                    Icon(
                        imageVector = if (showPassword.value) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription =
                        if (showPassword.value) stringResource(R.string.password_show_content_description)
                        else stringResource(R.string.password_hide_content_description),
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done,
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    firstInput.value = false
                    focusManager.clearFocus()
                }
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = modifier
                .background(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.secondaryContainer,
                )
        )

        if (!isValidPassword(passwordValue.value.text) && !firstInput.value) {
            errorState.value = true

            Text(
                text = stringResource(R.string.password_invalid_empty),
                color = MaterialTheme.colorScheme.error,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.offset(y = 5.dp)
            )
        } else {
            errorState.value = false
        }
    }
}

fun isValidEmail(email: String): Boolean {
    val emailRegex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()
    return email.matches(emailRegex)
}

fun isValidPassword(password: String): Boolean {
    return password.isNotBlank()
}