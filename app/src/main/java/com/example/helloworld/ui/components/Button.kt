package com.example.helloworld.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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

@Composable
fun Custombutton(
    btnText: String,
    onClick: () -> Unit,
    yOffset: Dp = 0.dp,
    customContentColor: Color = MaterialTheme.colorScheme.onPrimary,
    customContainerColor: Color = MaterialTheme.colorScheme.primary
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier= Modifier
            .fillMaxWidth()

    ) {
        Button(
            onClick,
            modifier = Modifier
                .width(300.dp)
                .height(60.dp)
                .padding(5.dp)
                .absoluteOffset(y = yOffset)
                .border(
                    shape = RoundedCornerShape(30.dp),
                    width = 3.dp,
                    color = MaterialTheme.colorScheme.primary
                ),
            colors = ButtonDefaults.buttonColors(
                contentColor = customContentColor,
                containerColor = customContainerColor
            )
        ) {
            Text(
                text = btnText
            )
        }
    }
}