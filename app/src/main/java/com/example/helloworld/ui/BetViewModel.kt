package com.example.helloworld.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.helloworld.models.JournalistModel
import com.google.gson.GsonBuilder
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
    init {
        fetchJournalists()
    }
    private fun fetchJournalists() {
        viewModelScope.launch {
            try {
                val call = RestClient().getService().getJournalists()
                val response = call.awaitResponse()

                if (response.isSuccessful) {
                    val getResponse = response.body()
                    Log.d("app_logs", getResponse.toString())
                }
            } catch (e: Exception) {
                Log.e("app_logs", "FAILED")
            }
        }
    }
}


interface Api {
    @Headers("Accept: Application/json")
    @GET("journalists")
    fun getJournalists(): Call<List<JournalistModel>>

    @POST("journalists")
    fun postUser(@Body journalist: JournalistModel)
}

class RestClient {
    private val service: Api
    fun getService(): Api {
        return service
    }

    init {
        val baseUrl = "http://localhost:5555/"
        val client = OkHttpClient.Builder()
        client.connectTimeout(2, TimeUnit.MINUTES)
        client.readTimeout(2, TimeUnit.MINUTES)
        client.writeTimeout(2, TimeUnit.MINUTES)
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