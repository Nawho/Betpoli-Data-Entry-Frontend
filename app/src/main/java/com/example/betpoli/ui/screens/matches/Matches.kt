package com.example.betpoli.ui.screens.matches

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.betpoli.models.Match
import com.example.betpoli.ui.BetViewModel
import com.example.betpoli.ui.components.CustomTopAppBar
import com.example.betpoli.ui.components.Custombutton
import com.example.betpoli.ui.components.MatchComponent


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MatchesScreen() {
    Surface(
        color= MaterialTheme.colorScheme.background,
        modifier = Modifier
            .fillMaxSize()
    ) {
        val matches = BetViewModel().MOCK_MATCHES

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
                    Text("Leto hot")
                }
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
                            count = matches.size,
                            key = { i: Int -> matches[i].id }
                        ) { i ->
                            MatchComponent(match = matches[i])

                        }
                    }
                }
            }
        }
    }
}