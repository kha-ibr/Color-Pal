package com.example.colorPal.ui.screens.generate

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material.icons.outlined.ChangeCircle
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material.icons.outlined.PictureAsPdf
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.CopyAll
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material.icons.rounded.Tune
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
import androidx.compose.runtime.getValue
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
import com.example.colorPal.ui.composable.bottomSheet.BottomSheet
import com.example.colorPal.ui.composable.bottomSheet.BottomSheetModel
import kotlinx.coroutines.launch


const val TAG: String = "ColorPaletteGeneratorScreen"

@SuppressLint("RememberReturnType")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColorPaletteGeneratorScreen(
    viewModel: ColorGeneratorViewModel = viewModel()
) {
    val padding = 16.dp
    val clipboardManager: ClipboardManager = LocalClipboardManager.current

    var cardId by remember { mutableIntStateOf(0) }
    var isSheetPaletteItemsVisible by remember { mutableStateOf(false) }
    var isMoreOptionSheetVisible by remember { mutableStateOf(false) }
    var isColorHarSheetVisible by remember { mutableStateOf(false) }
    var isExportSheetVisible by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }

    val sheetCardPaletteItems = listOf(
        BottomSheetModel(
            Icons.Rounded.Add, "Add Card", "Add Card"
        ), BottomSheetModel(
            Icons.Rounded.Remove, "Remove Card", "Remove Card"
        ), BottomSheetModel(
            Icons.Rounded.CopyAll, "Copy Color Code", "Copy Color Code"
        )
    )

    val exportPaletteItems = listOf(
        BottomSheetModel(
            Icons.Outlined.PictureAsPdf, "Export As PDF", "PDF"
        ),
        BottomSheetModel(
            Icons.Outlined.Image, "Export As Image", "Image"
        )
    )

    val bottomSheetContentForMoreOption = listOf(
        BottomSheetModel(
            Icons.Rounded.FavoriteBorder, "Save Color", "Save Color"
        ), BottomSheetModel(
            Icons.Rounded.Share,
            "Export Palette",
            "Export Palette",
            Icons.AutoMirrored.Rounded.KeyboardArrowRight
        ), BottomSheetModel(
            Icons.Outlined.ChangeCircle,
            "Color Harmony",
            "Color Harmony",
            Icons.AutoMirrored.Rounded.KeyboardArrowRight
        )
    )

    val colorHarmonySheetItems = listOf(
        BottomSheetModel(
            text = "Monochrome"
        ),
        BottomSheetModel(
            text = "Monochrome Dark",
        ),
        BottomSheetModel(
            text = "Monochrome Light",
        ),
        BottomSheetModel(
            text = "Analogic",
        ),
        BottomSheetModel(
            text = "Complement",
        ),
        BottomSheetModel(
            text = "Analogic Complement",
        ),
        BottomSheetModel(
            text = "Triad",
        ),
        BottomSheetModel(
            text = "Quad",
        ),
    )

    if (isSheetPaletteItemsVisible) {
        BottomSheet(items = sheetCardPaletteItems, onItemClick = {
            when (it) {
                0 -> {
                    viewModel.addCard()
                    isSheetPaletteItemsVisible = false
                }

                1 -> {
                    viewModel.removeCard(cardId)
                    isSheetPaletteItemsVisible = false
                }

                2 -> {
                    viewModel.copyColorCode(cardId, clipboardManager)
                    isSheetPaletteItemsVisible = false
                    scope.launch {
                        snackBarHostState.showSnackbar("Color code copied")
                    }
                }
            }
        }, onDismissSheet = { isSheetPaletteItemsVisible = false })
    }

    if (isMoreOptionSheetVisible) {
        BottomSheet(items = bottomSheetContentForMoreOption, onItemClick = { index ->
            when (index) {
                0 -> Log.d(TAG, "Save Button Clicked")
                1 -> {
                    isExportSheetVisible = true
                    isMoreOptionSheetVisible = false
                }

                2 -> {
                    isColorHarSheetVisible = true
                    isMoreOptionSheetVisible = false
                }
            }
        }, onDismissSheet = { isMoreOptionSheetVisible = false })
    }

    if (isColorHarSheetVisible) {
        BottomSheet(items = colorHarmonySheetItems, onItemClick = { index ->
            when (index) {
                0 -> Log.d(TAG, "Mono item Clicked")
                1 -> Log.d(TAG, "Remove Card Clicked")
                2 -> Log.d(TAG, "Copy Card Clicked")
            }
        }, onDismissSheet = { isColorHarSheetVisible = false })
    }

    if (isExportSheetVisible) {
        BottomSheet(items = exportPaletteItems, onItemClick = { index ->
            when (index) {
                0 -> Log.d(TAG, "PDF item Clicked")
                1 -> Log.d(TAG, "Image Item Clicked")
            }
        }, onDismissSheet = { isExportSheetVisible = false })
    }

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = "Generator")
        })
    }, bottomBar = {
        BottomAppBar {
            Text(text = "bottom bar")
        }
    },snackbarHost = {
        SnackbarHost(hostState = snackBarHostState)
    }, content = {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = 16.dp),
        ) {
            viewModel.colorStates.forEachIndexed { index, colorState ->
                ColorCard(
                    modifier = Modifier.weight(1f),
                    colorCardState = colorState,
                    onCardClick = {
                        isSheetPaletteItemsVisible = true
                        cardId = colorState.id
                        Log.d(TAG, "Card ID: ${colorState.id}")
                    },
                    onCardLock = { clickedId -> viewModel.toggleLock(clickedId) }
                )

                // Remove spacing on the last card
                if (index <= viewModel.colorStates.size - 1)
                    Spacer(modifier = Modifier.height(10.dp))
            }

            Row(
                modifier = Modifier.height(100.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = {
                    viewModel.colorStates.forEach { colorState ->
                        if (!colorState.isLocked) {
                            val newColor = Color(
                                (0..255).random(),
                                (0..255).random(),
                                (0..255).random()
                            )

                            viewModel.updateColor(colorState.id, newColor)
                        }
                    }
                }, modifier = Modifier.fillMaxWidth(.85f)) {
                    Text(text = "Generate")
                }

                Box(modifier = Modifier.fillMaxWidth(), Alignment.CenterEnd) {
                    IconButton(onClick = { isMoreOptionSheetVisible = true }) {
                        Icon(imageVector = Icons.Rounded.Tune, contentDescription = "More Option")
                    }
                }
            }
        }

    })
}

@Composable
fun ColorCard(
    modifier: Modifier = Modifier,
    textColor: Color = Color.Black,
    colorCardState: ColorCardState,
    onCardClick: () -> Unit,
    onCardLock: (Int) -> Unit
) {
    val padding = 16.dp

    Card(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .clickable { onCardClick() }
                .background(colorCardState.color),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                colorCardState.text,
                modifier = Modifier
                    .weight(5f)
                    .padding(start = padding),
                style = MaterialTheme.typography.bodyLarge,
                color = textColor
            )

            Box(
                modifier = modifier
                .fillMaxHeight()
                .width(50.dp)
                .clickable { onCardLock(colorCardState.id) },
                Alignment.CenterEnd
            ) {
                val icon = if (colorCardState.isLocked) Icons.Default.Lock else Icons.Default.LockOpen

                Icon(
                    imageVector = icon,
                    contentDescription = if (colorCardState.isLocked) "Unlock Palette" else "Lock Palette",
                    modifier = modifier.padding(end = padding)
                )
            }
        }
    }

}


@Preview
@Composable
fun ColorPaletteGeneratorScreenPreview() {
    val viewModel = viewModel<ColorGeneratorViewModel>()

    ColorPaletteGeneratorScreen(viewModel)
}
