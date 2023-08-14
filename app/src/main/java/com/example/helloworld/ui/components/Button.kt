package com.example.helloworld.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em

@Composable
fun Custombutton(
    modifier: Modifier = Modifier,
    btnText: String,
    onClick: () -> Unit,
    yOffset: Dp = 0.dp,
    customContentColor: Color = MaterialTheme.colorScheme.onSurface,
    customContainerColor: Color = MaterialTheme.colorScheme.primary,
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier= modifier
            .fillMaxWidth()

    ) {
        Button(
            onClick,
            modifier = Modifier
                .width(150.dp)
                .height(45.dp)
                .padding(0.dp)
                .absoluteOffset(y = yOffset)
                /*.border(
                    shape = RoundedCornerShape(30.dp),
                    width = 3.dp,
                    color = MaterialTheme.colorScheme.primary
                )*/,
            colors = ButtonDefaults.buttonColors(
                contentColor = customContentColor,
                containerColor = customContainerColor
            ),
            shape=RoundedCornerShape(corner = CornerSize(10.dp))
        ) {
            Text(
                text = btnText,
                fontSize = 3.em,
            )
        }
    }
}