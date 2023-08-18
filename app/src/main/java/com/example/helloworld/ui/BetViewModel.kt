package com.example.helloworld.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.helloworld.models.JournalistModel
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

class BetViewModel : ViewModel() {

    fun fetchJournalists() {
        viewModelScope.launch {
            try {
                val call = RestClient().getService().getJournalists()
                val response = call.awaitResponse()

                Log.d("app_logs", response.toString())

                if (response.isSuccessful) {
                    val getResponse = response.body()
                    Log.d("app_logs", getResponse.toString())
                }
            } catch (e: Exception) {
                Log.e("app_logs", "FAILED" + e.message + e.toString())
            }
        }
    }

    fun postUser(email: String, passwd: String) {
        viewModelScope.launch {
            val user = JsonObject()
            try {
                user.addProperty("fullName", "Funciona")
                user.addProperty("dni", 5555)
                user.addProperty("email", email)
                user.addProperty("passwd", passwd)
            }catch (e: Error){
                Log.e("app_logs","No tengo idea que paso Bv")
            }
            Log.d("app_logs", "Se creo correctamente: $user")
            val call = try {
                RestClient().getService().postUser(user)
            } catch (e: Exception) {
                TODO("Not yet implemented")
            }
            val response = call.awaitResponse()
            Log.d("app_logs", response.toString())
        }
    }
}


interface Api {
    @Headers("Accept: Application/json")
    @GET("/journalists/")
    fun getJournalists(): Call<List<JournalistModel>>

    @POST("/journalists/")
    fun postUser(@Body journalist: JsonObject): Call<Unit>
}

class RestClient {
    private val service: Api
    fun getService(): Api {
        return service
    }

    init {
        val baseUrl = "https://a209-190-189-176-246.ngrok-free.app"
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