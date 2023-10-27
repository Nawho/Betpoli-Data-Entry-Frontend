package com.example.betpoli.api_client

import com.example.betpoli.models.Match
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface ApiCalls {
    @POST("/journalists/")
    fun postLogin(@Body journalist: JsonObject): Call<Unit>

    @GET("/matches/page/{page}")
    fun getMatches(@Path("page") page: Int): Call<List<Match>>

    @GET("/partidos/:id")
    fun getMatch(id: Int): Call<Match>
}

class RestClient {
    private val service: ApiCalls
    fun getService(): ApiCalls {
        return service
    }

    init {
        val baseUrl = "https://110a-190-189-176-246.ngrok-free.app"
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
        service = retrofit.create(ApiCalls::class.java)
    }
}