package com.example.colorPal.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LockOpen
import androidx.compose.material.icons.rounded.Tune
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColorPaletteGeneratorScreen() {
    val numberOfCards by remember { mutableIntStateOf(3) }
    val padding = 16.dp

    val cardHeight = when (numberOfCards) {
        3 -> 190.dp
        4 -> 140.dp
        else -> 110.dp
    }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Generator")
            })
        },
        bottomBar = {
            BottomAppBar {
                Text(text = "bottom bar")
            }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier.padding(start = padding, end = padding).weight(.8f)
            ) {
                items(numberOfCards) { index ->
                    ColorPaletteCard(cardHeight)

                    if (index < numberOfCards - 1) // Remove spacing on the last card
                        Spacer(modifier = Modifier.height(10.dp))
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = padding, end = padding)
                    .weight(.1f)
            ) {
                Button(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth(.9f)) {
                    Text(text = "Generate")
                }

                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Rounded.Tune, contentDescription = "Tune")
                }
            }
        }
    }
}

@Composable
fun ColorPaletteCard(cardHeight: Dp) {
    val padding = 16.dp

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(5.dp))
            .background(Color.Green)
            .fillMaxWidth()
            .height(cardHeight)
            .padding(top = padding)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = padding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "#fffffff")
            Icon(
                imageVector = Icons.Rounded.LockOpen,
                contentDescription = "Lock",
                modifier = Modifier.clickable { TODO("implement the locking mechanism") })
        }
    }
}

@Preview
@Composable
fun ColorPaletteCardPreview() {
    //ColorPaletteCard()
}

@Preview
@Composable
fun ColorPaletteGeneratorScreenPreview() {
    ColorPaletteGeneratorScreen()
}