package com.example.colorPal.ui.screens.favorite

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.colorPal.data.database.ColorInfo
import com.example.colorPal.ui.component.bottomSheet.BottomSheet
import com.example.colorPal.ui.component.bottomSheet.BottomSheetModel

private const val TAG = "FavoriteScreen"

@SuppressLint("CheckResult")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(viewModel: FavoriteViewModel = viewModel()) {
    val padding = 16.dp

    val colors = viewModel.allColors.observeAsState(emptyList())

    Scaffold(topBar = { TopAppBar(title = { Text(text = "Favorite") }) },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = padding)
                    .fillMaxSize()
            ) {
                FavoriteColorCard(colors = colors)
            }
        })
}

@Composable
fun FavoriteColorCard(colors: State<List<ColorInfo>>, viewModel: FavoriteViewModel = viewModel()) {
    val groupedItems = colors.value.groupBy { it.commonality }
    var showSheet by remember { mutableStateOf(false) }

    var common by remember { mutableIntStateOf(0) }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        groupedItems.forEach { (commonality, color) ->

            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .clickable {
                            if (commonality != null) {
                                common = commonality
                            }

                            showSheet = true
                        }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                    ) {
                        color.forEach { color ->
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .background(
                                        color = Color(
                                            android.graphics.Color.parseColor(
                                                color.hex
                                            )
                                        )
                                    )
                                    .weight(1f)
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }

    val items =
        listOf(BottomSheetModel(icon = Icons.Default.DeleteOutline, text = "Delete Palette"))

    if (showSheet)
        BottomSheet(
            items = items,
            onItemClick = {
                viewModel.onCardClick(common)
                showSheet = false
            },
            onDismissSheet = { showSheet = false })
}

@Preview
@Composable
private fun FavPrev() {
//    FavoriteScreen()
}