package com.example.betpoli.ui.screens.login

import androidx.lifecycle.ViewModel
import com.example.betpoli.api_client.LoginRepository

class LoginViewModel : ViewModel() {
    private val loginHandler = LoginRepository()

    suspend fun postLogin(email: String, password: String) {
        return loginHandler.postLogin(email, password)
    }
}