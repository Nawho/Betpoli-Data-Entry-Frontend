package com.example.betpoli.ui.screens.matches

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.betpoli.api_client.MatchesRepository
import com.example.betpoli.api_client.Resource
import com.example.betpoli.models.Match
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MatchesViewModel: ViewModel()  {
    private val matchesRepo = MatchesRepository()
    data class ScreenState(
        val items: List<Match> = emptyList(),
        val isLoading: Boolean = false,
        val error: String = "",
        val page: Int = 0,
        val endReached: Boolean = false
    )

    private val _state = MutableStateFlow(ScreenState())
    val state = _state.asStateFlow()


    private fun onLoadingChanged(loadingState: Boolean) {
        _state.update { it.copy(isLoading = loadingState) }
    }
    private fun onSuccessfulCall(newItemList: List<Match>, newPage: Int) {
        _state.update {
            it.copy(
                isLoading = false,
                items = it.items + newItemList,
                page = newPage,
                endReached = newItemList.isEmpty()
            )
        }
        Log.d("app_logs", "State updated! $newPage")
    }

    private fun onErrorCall(errorMsg: String) {
        _state.update {
            it.copy(
                isLoading = false,
                error = errorMsg
            )
        }
    }

    /*
    suspend fun getMatch(id: Int): Response<Match> {
        return matchesRepo.getMatch(id)
    }
    */

    suspend fun getMatches() {
        onLoadingChanged(true);
        val currentPage = _state.value.page
        val response = matchesRepo.getMatches(currentPage)

        when (response) {
            is Resource.Success -> {
                onSuccessfulCall(response.data!!,  currentPage + 1)
                Log.d("app_logs", response.data.toString())
            }

            is Resource.Error -> {
                response.message?.let { onErrorCall(it) }
                Log.d("app_logs", response.message!!.toString())
            }
        }

        onLoadingChanged(false);
    }
}