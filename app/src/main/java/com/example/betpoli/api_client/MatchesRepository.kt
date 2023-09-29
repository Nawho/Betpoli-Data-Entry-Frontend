package com.example.betpoli.api_client

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.betpoli.models.Match
import com.example.betpoli.models.MatchState
import retrofit2.Response
import retrofit2.awaitResponse
import java.time.LocalDate

class MatchesHandler {
    suspend fun getMatch(id: Int): Response<Match> {
        val call = RestClient().getService().getMatch(id)
        val response = call.awaitResponse()
        Log.d("app_logs", response.toString())

        return response
    }

    suspend fun getMatches(): Response<List<Match>> {
        val call = RestClient().getService().getMatches()
        val response = call.awaitResponse()
        Log.d("app_logs", response.toString())

        return response
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