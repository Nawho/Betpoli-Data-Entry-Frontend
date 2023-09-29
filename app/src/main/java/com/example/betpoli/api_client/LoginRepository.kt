package com.example.betpoli.api_client

import android.util.Log
import com.google.gson.JsonObject
import retrofit2.awaitResponse

class LoginHandler {
    suspend fun postLogin(email: String, password: String) {
        try {
            val loginRequest = JsonObject()
            loginRequest.addProperty("email", email)
            loginRequest.addProperty("passwd", password)

            val call = RestClient().getService().postLogin(loginRequest)
            val response = call.awaitResponse()
            Log.d("app_logs", response.toString())
        } catch (e: Exception) {
            Log.e("app_logs", e.message.toString())
        }
    }
}