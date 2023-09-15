package com.example.betpoli.ui.screens.landing

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.betpoli.ui.components.Custombutton


@Composable
fun LandingScreen(
    onLoginClicked: () -> Unit,
) {
    Surface(
        color= MaterialTheme.colorScheme.background,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(Modifier.fillMaxSize()) {
            /*Image(
                painter = painterResource(id = R.drawable.future_background),
                contentDescription = stringResource(id = R.string.landing_bg_description),
                modifier = Modifier
                    .fillMaxSize()
                    .scale(1.4f)
            )*/

            Column(modifier = Modifier
                .absoluteOffset(0.dp, 200.dp)
            ) {
                LandingTitle()

                Column(
                    modifier = Modifier
                        .absoluteOffset(y = 250.dp)
                ) {
                    /*Custombutton(
                        btnText = "Create account",
                        onClick = {
                            Log.d("app_logs", "Create Account clicked!")
                            onRegisterClicked()
                        },
                        yOffset = 0.dp
                    )*/

                    Custombutton(
                        btnText = "Sign in",
                        onClick = {
                            Log.d("app_logs", "Sign in clicked!")
                            onLoginClicked()
                        },
                        yOffset = 5.dp,
                    )
                }
            }
        }
    }
}

@Composable
fun LandingTitle() {
    Box(
        modifier = Modifier
            .height(10.dp)
            .fillMaxWidth()
    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()

    ) {
        Text(
            text = "POLICTL",
            textAlign = TextAlign.Center,
            fontSize = 8.em,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            style = TextStyle.Default.copy(
                fontSize = 64.sp,
                shadow = Shadow(
                    color = Color.Black,
                    offset = Offset(0f, 0f),
                    blurRadius = 5f
                ),
                background = Color.Transparent
            ),

            )
        Text(
            text = "BetPoli Data-entry Control Panel",
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            fontSize = 3.em,
            lineHeight = 1.em,
            fontWeight = FontWeight.Normal
        )
    }
}