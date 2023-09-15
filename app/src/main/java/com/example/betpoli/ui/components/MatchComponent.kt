package com.example.betpoli.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.betpoli.R
import com.example.betpoli.models.Match
import com.example.betpoli.models.MatchState


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
                .width(30.dp)
                .height(30.dp)
        )
        Text(teamName)
    }
}

@Composable
fun ScoreNumber(
    modifier: Modifier = Modifier,
    score: Int
) {
    Text(text = "$score")
}

@Composable
fun middleSection(
    modifier: Modifier = Modifier,
    matchState: MatchState
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("VS")
        Text(matchState.toString())
    }
}


@Composable()
fun MatchComponent(
    modifier: Modifier = Modifier,
    match: Match
) {
    Box(
        modifier = Modifier
            .width(320.dp)
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