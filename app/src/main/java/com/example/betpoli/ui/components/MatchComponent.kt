package com.example.betpoli.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.betpoli.models.Match


@Composable
fun Team(
    teamName: String,
    teamLogoURL: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = "https://upload.wikimedia.org/wikipedia/en/thumb/0/0c/Liverpool_FC.svg/640px-Liverpool_FC.svg.png",
            contentDescription = "Translated description of what the image contains",
            modifier = Modifier
                .width(48.dp)
                .height(48.dp)
        )
        Text(teamName, fontWeight = FontWeight.Bold, fontSize = 12.sp)
    }
}

@Composable
fun ScoreNumber(
    modifier: Modifier = Modifier,
    score: Int
) {
    Text(text = "$score", fontWeight = FontWeight.Bold, fontSize = 25.sp)
}

@Composable
fun middleSection(
    modifier: Modifier = Modifier,
    matchState: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("VS")
        //Text(text = if (matchState.toString() == "MATCH_OVER") "Terminado"
        //else "Comenzará pronto")
    }
}


@Composable()
fun MatchComponent(
    modifier: Modifier = Modifier,
    match: Match
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(80.dp)
            .background(MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Team(match.local, "https://upload.wikimedia.org/wikipedia/en/thumb/0/0c/Liverpool_FC.svg/640px-Liverpool_FC.svg.png")
            ScoreNumber(score = match.golesLocal)
            middleSection(matchState = match.matchState)
            ScoreNumber(score = match.golesVisitante)
            Team(match.visitante, "")
        }
    }
}