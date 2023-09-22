package com.example.betpoli.ui

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.betpoli.models.Match
import com.example.betpoli.models.MatchState
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import java.time.LocalDate
import java.util.concurrent.TimeUnit

class BetViewModel : ViewModel() {
    fun postUser(email: String, passwd: String) {
        try {
            viewModelScope.launch {
                val user = JsonObject()
                user.addProperty("fullName", "Funciona")
                user.addProperty("dni", 5555)
                user.addProperty("email", email)
                user.addProperty("passwd", passwd)

                val call = RestClient().getService().postUser(user)
                val response = call.awaitResponse()
                Log.d("app_logs", response.toString())
            }
        } catch (e: Exception) {
            Log.e("app_logs", e.message.toString())
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    val MOCK_MATCHES: List<Match> = listOf(
        Match("San Lore", "Boquita", 4, 4, MatchState.NOT_STARTED, LocalDate.now().toString(), 1),
        Match("Racin", "Platense", 14, 0, MatchState.MATCH_OVER, LocalDate.now().toString(), 2),
        Match("RiBer", "Huracan", 1, 0, MatchState.MATCH_OVER, LocalDate.now().toString(), 3),
        Match("Lanus", "Independiente", 5, 6, MatchState.MATCH_OVER, LocalDate.now().toString(), 4),
    )
}


interface Api {
    @POST("/journalists/")
    fun postUser(@Body journalist: JsonObject): Call<Unit>
}

class RestClient {
    private val service: Api
    fun getService(): Api {
        return service
    }

    init {
        val baseUrl = "https://399b-190-189-176-246.ngrok-free.app/"
        val client = OkHttpClient.Builder()
        client.connectTimeout(5, TimeUnit.SECONDS)
        client.readTimeout(5, TimeUnit.SECONDS)
        client.writeTimeout(5, TimeUnit.SECONDS)
        val gson = GsonBuilder()
            .serializeNulls()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client.build())
            .build()
        service = retrofit.create(Api::class.java)
    }
}