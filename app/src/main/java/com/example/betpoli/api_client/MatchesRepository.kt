package com.example.betpoli.api_client

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.betpoli.models.Match
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import retrofit2.Response
import retrofit2.awaitResponse
import java.time.LocalDate

class MatchesRepository {
    val _matchesFlow = MutableStateFlow<List<Match>>(emptyList());
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

    suspend fun getMatches(page: Int): Response<List<Match>> {


        return RestClient().getService().getMatches().awaitResponse()
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