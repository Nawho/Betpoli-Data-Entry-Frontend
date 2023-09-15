package com.example.betpoli
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.betpoli.ui.components.BetPoliApp
import com.example.betpoli.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
        AppTheme {
            BetPoliApp()
        }
    }
} }
