package com.example.betpoli.api_client

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.betpoli.models.Match
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import retrofit2.Response
import retrofit2.awaitResponse
import java.time.LocalDate


sealed class Resource<T>(val data: T?, val message: String?) {
    class Success<T>(data: T) : Resource<T>(data, null)
    class Error<T>(message: String) : Resource<T>(null, message)
}
class MatchesRepository {
    private val _matchesFlow = MutableStateFlow<List<Match>>(emptyList());
    val matchesFlow = _matchesFlow.asSharedFlow()

    init {
        sharedFlowInit()
    }

    fun sharedFlowInit() {

    }
    suspend fun getMatch(id: Int): Response<Match> {
        val call = RestClient().getService().getMatch(id)
        val response = call.awaitResponse()
        Log.d("app_logs", response.toString())

        return response
    }

    private fun getItems(){

    }

    fun getMatches(page: Int): Resource<List<Match>> {
        val response = RestClient().getService().getMatches()
        val resBody = response.body()

        return try {
            if (response.isSuccessful && resBody != null) {
                Resource.Success(resBody)
            }  else {
                Resource.Error("ERROR: An unexpected error with the API call occurred.")
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "ERROR: An error occurred with Retrofit.")
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun _getMatchesMock(): List<Match> {
        val MOCK_MATCHES: List<Match> = listOf(
            Match("San Lore", "Boquita", 4, 4, "NOT_STARTED", LocalDate.now().toString(), "1"),
            Match("Racin", "Platense", 14, 0, "MATCH_OVER", LocalDate.now().toString(), "2"),
            Match("RiBer", "Huracan", 1, 0, "MATCH_OVER", LocalDate.now().toString(), "3"),
            Match("Lanus", "Independiente", 5, 6, "MATCH_OVER", LocalDate.now().toString(), "4"),
        )

        return MOCK_MATCHES
    }
}