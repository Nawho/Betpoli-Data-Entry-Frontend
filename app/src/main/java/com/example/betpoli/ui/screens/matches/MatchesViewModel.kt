package com.example.betpoli.ui.screens.matches

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.betpoli.api_client.MatchesRepository
import com.example.betpoli.api_client.Resource
import com.example.betpoli.models.Match
import com.example.betpoli.ui.BetViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import retrofit2.Response

class MatchesViewModel: ViewModel()  {
    //private val _matches = MutableLiveData<List<Match>>()
    //val matches: LiveData<List<Match>> = _matches
    private val matchesRepo = MatchesRepository()
    data class ScreenState(
        val items: List<Match> = emptyList(),
        val isLoading: Boolean = false,
        val error: String = "",
        val paginationKey: Int = 0,
        val endReached: Boolean = false
    )

    private val _state = MutableStateFlow(ScreenState())
    val state = _state.asStateFlow()


    private fun onLoadingChanged(loadingState: Boolean) {
        _state.update { it.copy(isLoading = loadingState) }
    }
    private fun onSuccessfulCall(newItemList: List<Match>, newPaginationKey: Int) {
        _state.update {
            it.copy(
                isLoading = false,
                items = it.items + newItemList,
                paginationKey = newPaginationKey,
                endReached = newItemList.isEmpty()
            )
        }
    }

    private fun onErrorCall(errorMsg: String) {
        _state.update {
            it.copy(
                isLoading = false,
                error = errorMsg
            )
        }
    }

    suspend fun getMatches() {
        onLoadingChanged(true);
        val response = matchesRepo.getMatches(1);

        when (response) {
            is Resource.Success -> {
                //_matches.value = response.data!!
                onSuccessfulCall(response.data!!, 1)
                Log.d("app_logs", response.data.toString())
            }

            is Resource.Error -> {
                //_matches.value = emptyList()
                response.message?.let { onErrorCall(it) }
                Log.d("app_logs", response.message!!.toString())
            }
        }

        onLoadingChanged(false);


        /*isloading = true
                val response = matchesRepo.getMatches(
                    page = _state.value.paginationKey
                )
                    /*.body()!!.onEach { result ->
                    when(result) {
                    }
                }*/
                _matches.value = response.body()

                onSuccessfulCall(loading 0 false, response.body(), _state.value.paginationKey+1)

                Log.d("app_logs", response.body().toString())*/
    }

    suspend fun getMatch(id: Int): Response<Match> {
        return matchesRepo.getMatch(id)
    }
}