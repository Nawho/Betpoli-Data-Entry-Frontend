package com.example.betpoli.ui.screens.matches

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.betpoli.models.Match
import com.example.betpoli.ui.BetViewModel
import com.example.betpoli.ui.components.CustomTopAppBar
import com.example.betpoli.ui.components.Custombutton
import com.example.betpoli.ui.components.MatchComponent
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import retrofit2.Response


@Composable
fun InfiniteScrollList(
    lazyListState: LazyListState,
    buffer: Int = 2,
    onLoadMore: () -> Unit,
) {
    val loadMore = remember {
        derivedStateOf {
            val layoutInfo = lazyListState.layoutInfo
            val totalItems = layoutInfo.totalItemsCount
            val lastVisibleItemIndex = (layoutInfo.visibleItemsInfo.lastOrNull()?.index ?:0) + 1

            lastVisibleItemIndex > (totalItems-buffer)
        }
    }

    LaunchedEffect(loadMore) {
        snapshotFlow {
            loadMore.value
        }

            .distinctUntilChanged()
            .collect { onLoadMore() }
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MatchesScreen() {
    Surface(
        color= MaterialTheme.colorScheme.background,
        modifier = Modifier
            .fillMaxSize()
    ) {
        val matchesViewModel = MatchesViewModel()
        val matches = matchesViewModel.matches.observeAsState(initial = emptyList())

        LaunchedEffect(key1 = "default", block = {
            matchesViewModel.getMatches()
        })
        
        Scaffold(
            topBar = {
                CustomTopAppBar()
            },
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(25.dp)
            ) {
                Spacer(modifier = Modifier.height(25.dp))
                Box(modifier = Modifier
                    .width(350.dp)
                    .height(40.dp)
                    .background(color = MaterialTheme.colorScheme.primaryContainer)
                ){
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("HOY MAÑANA PASADO", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
                    }
                }

                if (matches.value.isEmpty()) {
                    Text("Loading")
                    CircularProgressIndicator()
                } else {
                    Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .background(color = MaterialTheme.colorScheme.primaryContainer)

                ) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(
                                count = matches.value.size,
                                key = { i: Int -> matches.value[i].id }
                            ) { i ->
                                MatchComponent(match = matches.value[i])
                            }
                        }
                    }
                }
            }
        }
    }
}