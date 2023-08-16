package com.example.helloworld.ui.screens.login

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.example.helloworld.R
import com.example.helloworld.models.JournalistModel
import com.example.helloworld.ui.BetViewModel
import com.example.helloworld.ui.components.Custombutton
import com.example.helloworld.ui.components.PasswordTextField
import com.example.helloworld.ui.components.EmailTextField
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Headers
import java.util.concurrent.TimeUnit

@Composable
fun LoginScreen() {
    val emailError = remember { mutableStateOf(true) }
    val passwordError = remember { mutableStateOf(true) }

    Log.d("app_logs", "Email error: " + emailError.value)

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
                        label = "Email",
                        errorState = emailError,
                        modifier = Modifier
                            .width(300.dp)
                            .background(color = MaterialTheme.colorScheme.secondaryContainer)
                    )

                    Spacer(Modifier.height(25.dp))

                    PasswordTextField(
                        label = "Contraseña",
                        errorState = passwordError,
                        modifier = Modifier
                            .width(300.dp)
                            .background(color = MaterialTheme.colorScheme.secondaryContainer)
                    )

                    Spacer(Modifier.height(50.dp))

                    Custombutton(
                        btnText = "Login",
                        enabled = !emailError.value && !passwordError.value,
                        onClick = {
                            Log.d("app_logs", "Login clicked")
                            val viewmodel = BetViewModel()
                            viewmodel.fetchJournalists()
                        },
                    )


                    Row(
                        modifier = Modifier.offset(y = 20.dp)
                    ) {
                        Text(
                            text = "¿No eres periodista?",
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = " Ingresa Aquí",
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
        contentDescription = "betpoli logo",
        modifier = Modifier
            .width(350.dp)
            .padding(vertical = 50.dp)
    )
}
