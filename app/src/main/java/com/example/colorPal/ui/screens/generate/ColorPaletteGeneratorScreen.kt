package com.example.colorPal.ui.screens.generate

import android.annotation.SuppressLint
import android.graphics.Color.parseColor
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.CopyAll
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.colorPal.model.ColorRepresentation
import com.example.colorPal.model.Colors
import com.example.colorPal.ui.composable.bottomSheet.BottomSheet
import com.example.colorPal.ui.composable.bottomSheet.BottomSheetModel
import kotlinx.coroutines.launch


private const val TAG: String = "ColorPaletteGeneratorScreen"

@SuppressLint("RememberReturnType", "CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColorPaletteGeneratorScreen(
    viewModel: ColorGeneratorViewModel = viewModel()
) {
    val clipboardManager: ClipboardManager = LocalClipboardManager.current

    val colorLiveData by viewModel.colorLiveData.observeAsState()
    val saveFetchedColors by viewModel.saveFetchedColors.observeAsState()

    var cardIndex by remember { mutableIntStateOf(0) }
    var maxCardDisplayed by remember { mutableIntStateOf(3) }

    var isSheetPaletteItemsVisible by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.fetchColorScheme(viewModel.getHexValue(), mode = "monochrome")
    }

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = "Generator")
        })
    }, bottomBar = {
        BottomAppBar {
            Text(text = "bottom bar")
        }
    }, snackbarHost = {
        SnackbarHost(hostState = snackBarHostState)
    }, content = {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = 16.dp)
                .fillMaxSize(),
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.85f)
            ) {
                saveFetchedColors?.forEachIndexed { index, color ->
                    ColorCard(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        color = color,
                        onCardClick = {
                            cardIndex = index
                            isSheetPaletteItemsVisible = true
                        })
                    // Remove spacing on the last card
                    if (index <= colorLiveData!!.colors!!.size) Spacer(
                        modifier = Modifier.height(
                            10.dp
                        )
                    )
                }
            }

            GenerateButton(viewModel)
        }
    })

    val sheetCardPaletteItems = listOf(
        BottomSheetModel(
            Icons.Rounded.Add, "Add Card", "Add Card"
        ), BottomSheetModel(
            Icons.Rounded.Remove, "Remove Card", "Remove Card"
        ), BottomSheetModel(
            Icons.Rounded.CopyAll, "Copy Color Code", "Copy Color Code"
        )
    )

    if (isSheetPaletteItemsVisible) {
        BottomSheet(items = sheetCardPaletteItems, onItemClick = {
            when (it) {
                0 -> {
                    if (maxCardDisplayed > 0) {
                        viewModel.addCard()
                        maxCardDisplayed--
                    }
                    //isSheetPaletteItemsVisible = false
                }

                1 -> {
                    viewModel.removeCard(cardIndex)
                    maxCardDisplayed++
                    //isSheetPaletteItemsVisible = false
                }

                2 -> {
                    viewModel.copyColorCode(cardIndex, clipboardManager, ColorRepresentation.RGB)
                    //isSheetPaletteItemsVisible = false
                    scope.launch {
                        snackBarHostState.showSnackbar("Color copied!")
                    }
                }
            }
        }, onDismissSheet = { isSheetPaletteItemsVisible = false })
    }
}

@Composable
fun ColorCard(
    modifier: Modifier = Modifier,
    color: Colors,
    onCardClick: () -> Unit,
) {
    val padding = 16.dp

    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .clickable { onCardClick() }
            .background(color = Color(parseColor(color.hex?.value))),
            verticalAlignment = Alignment.CenterVertically) {
            color.hex?.clean?.let {
                Text(
                    text = it,
                    modifier = Modifier
                        .weight(5f)
                        .padding(start = padding),
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(parseColor(color.contrast?.value))
                )
            }
        }
    }

}

@Composable
fun GenerateButton(
    viewModel: ColorGeneratorViewModel = viewModel()
) {
    Row(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(onClick = {
            val generateRandomHex = viewModel.getHexValue()
            viewModel.fetchColorScheme(
                hexCode = generateRandomHex, mode = "monochrome"
            )
        }, modifier = Modifier.fillMaxWidth(.85f)) {
            Text(text = "Generate")
        }

        Box(modifier = Modifier.fillMaxWidth(), Alignment.CenterEnd) {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Rounded.FavoriteBorder,
                    contentDescription = "Save Palette"
                )
            }
        }
    }
}

@Preview
@Composable
fun ColorPaletteGeneratorScreenPreview() {
    val viewModel = viewModel<ColorGeneratorViewModel>()
}
