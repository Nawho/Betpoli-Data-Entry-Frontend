package com.example.betpoli.ui.screens.login

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import com.example.betpoli.R
import com.example.betpoli.ui.components.Custombutton
import com.example.betpoli.ui.components.PasswordTextField
import com.example.betpoli.ui.components.EmailTextField
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    onceLogged: () -> Unit,
) {
    val emailValue = remember { mutableStateOf(TextFieldValue("")) }
    val passwordValue = remember { mutableStateOf(TextFieldValue("")) }
    val emailError = remember { mutableStateOf(true) }
    val passwordError = remember { mutableStateOf(true) }
    val focusManager = LocalFocusManager.current
    val loginViewModel = LoginViewModel()

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
            BetpoliLogo()

            Card(
                modifier = Modifier
                    .height(420.dp)
                    .padding(horizontal = 25.dp),
                shape = RoundedCornerShape(corner = CornerSize(15.dp)),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                ),
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(horizontal = 30.dp, vertical = 50.dp)
                ) {
                    EmailTextField(
                        errorState = emailError,
                        emailValue = emailValue,
                        modifier = Modifier
                            .width(300.dp)
                    )

                    Spacer(Modifier.height(25.dp))

                    PasswordTextField(
                        errorState = passwordError,
                        passwordValue = passwordValue,
                        modifier = Modifier
                            .width(300.dp)
                    )

                    Spacer(Modifier.height(50.dp))

                    Custombutton(
                        btnText = stringResource(R.string.login_btn_text),
                        enabled = !emailError.value && !passwordError.value,
                        onClick = {
                            Log.d("app_logs", "Login clicked")
                            loginViewModel.viewModelScope.launch {
                                loginViewModel.postLogin(emailValue.value.text, passwordValue.value.text)
                                onceLogged()
                            }
                        },
                    )


                    Row(
                        modifier = Modifier.offset(y = 20.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.not_journalist_1),
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = stringResource(R.string.not_journalist_2),
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.SemiBold,
                            textDecoration = TextDecoration.Underline
                        )
                    }

                }
            }
        }
    }
}

@Composable
fun BetpoliLogo() {
    Image(
        painter = painterResource(
            id = if (isSystemInDarkTheme()) R.drawable.betpoli_logo_celeste_dark
            else R.drawable.betpoli_logo_celeste_light),
        contentDescription = stringResource(R.string.betpoli_logo),
        modifier = Modifier
            .width(350.dp)
            .padding(vertical = 50.dp)
    )
}
