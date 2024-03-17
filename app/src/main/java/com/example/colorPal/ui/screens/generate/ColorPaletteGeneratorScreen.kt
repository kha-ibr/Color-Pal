package com.example.colorPal.ui.screens.generate

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.rounded.Tune
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColorPaletteGeneratorScreen() {
    val numberOfCards by remember { mutableIntStateOf(4) }
    val padding = 16.dp

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = "Generator")
        })
    }, bottomBar = {
        BottomAppBar {
            Text(text = "bottom bar")
        }
    }, content = { it ->
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            repeat(5) {
                ColorPaletteCard(
                    text = "Blue",
                    color = Color.Blue,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = padding, end = padding)
                    .weight(1f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth(.9f)) {
                    Text(text = "Generate")
                }

                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Rounded.Tune, contentDescription = "Tune")
                }
            }
        }

    })
}

@Composable
fun ColorPaletteCard(
    modifier: Modifier = Modifier,
    text: String,
    color: Color,
    cardShape: Shape = RoundedCornerShape(8.dp)
) {
    val padding = 16.dp

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = padding, end = padding)
            .background(color, shape = cardShape)
            .padding(padding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = text, style = MaterialTheme.typography.bodyLarge, color = Color.White
        )

        Icon(
            imageVector = Icons.Default.Lock,
            contentDescription = "Lock Icon",
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Preview
@Composable
fun ColorPaletteCardPreview() {
    /*Column {
        ColorPaletteCard(
            text = "Blue", color = Color.Blue, modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
    }*/
}

@Preview
@Composable
fun ColorPaletteGeneratorScreenPreview() {
    ColorPaletteGeneratorScreen()
}

