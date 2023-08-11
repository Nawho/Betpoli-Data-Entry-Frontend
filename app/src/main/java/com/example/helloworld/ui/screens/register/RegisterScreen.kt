package com.example.helloworld.ui.screens.register

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.example.helloworld.models.JournalistModel
import com.example.helloworld.ui.BetViewModel
import com.example.helloworld.ui.components.Custombutton
import com.example.helloworld.ui.components.PasswordTextField
import com.example.helloworld.ui.components.CustomTextField
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
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.width(300.dp)

            )

            PasswordTextField(
                label = "Password",
                modifier = Modifier
                    .offset(y = 15.dp)
                    .width(300.dp)
                    .clip(RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp))

            )

            Custombutton(
                btnText = "Login",
                onClick = {
                    Log.d("app_logs", "Register clicked")
                },
                yOffset = 30.dp
            )
        }
    }
}

@Composable
fun RegisterTitle() {
    Text(
        text = "Login to access the platform",
        color = MaterialTheme.colorScheme.primary,
        textAlign = TextAlign.Center,
        fontSize = 5.em,
        lineHeight = 1.em,
        fontWeight = FontWeight.Normal,
        modifier = Modifier
            .padding(vertical = 20.dp, horizontal = 15.dp)
    )
    
    LaunchedEffect(Unit) {
        Log.d("app_logs", "launched")
        val viewmodel = BetViewModel()
    }
}