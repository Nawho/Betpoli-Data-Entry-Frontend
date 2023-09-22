package com.example.betpoli.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AccountTree
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultShadowColor
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.betpoli.R

@Composable
fun Logo(
    modifier: Modifier = Modifier
) {
    if (isSystemInDarkTheme()) {
        Image(painterResource(id = R.drawable.betpoli_logo_celeste_light), "Betpoli logo", modifier = modifier)
    } else {
        Image(painterResource(id = R.drawable.betpoli_logo_celeste_dark), "Betpoli logo", modifier = modifier)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.inversePrimary,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
            title = {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Logo(
                        modifier = Modifier
                            .width(100.dp)
                    )

                    IconButton(onClick = { /*TODO*/ },) {
                        Icon(
                            modifier = Modifier.size(31.dp),
                            imageVector = Icons.Filled.AccountCircle,
                            contentDescription = "User button",
                            tint = Color.White
                        )
                    }
                }
            },
            modifier = Modifier
                .height(75.dp)
                .shadow(elevation = 10.dp)
        )
    }
}