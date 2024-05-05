package com.example.colorPal.ui.screens.favorite

import android.graphics.Color.parseColor
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.colorPal.data.database.ColorInfo

private const val TAG = "FavoriteScreen"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(viewModel: FavoriteViewModel = viewModel()) {
    val padding = 16.dp
    val colors by viewModel.colorList.observeAsState()
    val colorList = mutableListOf<ColorInfo>()

    viewModel.getAllColor()

    colors?.forEach {
        colorList.addAll(listOf(it))
    }

    Log.d(TAG, colorList.toString())

    Scaffold(topBar = { TopAppBar(title = { Text(text = "Favorite") }) },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = padding)
                    .fillMaxSize()
            ) {
                FavoriteColorCard(colors = colorList)
            }
        }
    )
}

@Composable
fun FavoriteColorCard(colors: List<ColorInfo>?) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {

        if (colors == null) {
            // Show a loading indicator (e.g., CircularProgressIndicator())
            CircularProgressIndicator(modifier = Modifier.fillMaxSize().align(Alignment.CenterHorizontally))
        } else {
            colors.forEachIndexed { _, colorInfo ->

                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .background(color = Color(parseColor(colorInfo.hex.toString())))
                        .weight(1f)
                )
            }
        }
        /*
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.6f)
        ) {
            colors?.forEachIndexed { index, colorInfo ->
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .background(
                            color = Color(
                                parseColor(
                                    colorInfo.hex
                                        ?.get(index)
                                        ?.toString()
                                )
                            )
                        )
                        .weight(1f)
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.6f)
        ) {
            colors?.forEach {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .background(color = Color(parseColor(it.hex?.forEach {it2 -> it2.toString() }.toString())))
                        .weight(1f)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    Arrangement.SpaceBetween,
                    Alignment.CenterVertically
                ) {
                    Text(
                        text = it.name?.forEach { it2-> it2.toString() }.toString(),
                        modifier = Modifier.padding(start = 10.dp)
                    )

                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Default.MoreHoriz,
                            contentDescription = "Options"
                        )
                    }
                }
            }
        }*/
    }
}

@Preview
@Composable
private fun FavPrev() {
//    FavoriteScreen()
}