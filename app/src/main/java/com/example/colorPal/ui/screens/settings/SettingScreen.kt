package com.example.colorPal.ui.screens.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen() {
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Settings") })
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(text = "Settings")
        }
    }
}

@Composable
fun GeneralCard(
    onCardClick: () -> Unit = {}
) {
    Scaffold { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            Text(
                text = "General",
                modifier = Modifier.padding(bottom = 8.dp),
                fontSize = 16.sp
            )

            Card(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .clickable { onCardClick() }) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "App Theme", fontWeight = FontWeight.Medium)

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "Light", modifier = Modifier.padding(end = 4.dp))
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = null,
                            tint = Color(0xFF525252)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewCard() {
    GeneralCard()
}