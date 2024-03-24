package com.example.colorPal.ui.screens.generate

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.colorPal.ui.composable.bottomSheet.BottomSheet
import com.example.colorPal.ui.composable.bottomSheet.BottomSheetModel


const val TAG: String = "ColorPaletteGeneratorScreen"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColorPaletteGeneratorScreen() {

    val numberOfCards by remember { mutableIntStateOf(4) }
    val padding = 16.dp

    var isSheetPaletteItemsVisible by remember { mutableStateOf(false) }
    var isMoreOptionSheetVisible by remember { mutableStateOf(false) }
    var isColorHarSheetVisible by remember { mutableStateOf(false) }
    var isExportSheetVisible by remember { mutableStateOf(false) }

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
        BottomSheet(items = sheetCardPaletteItems, onItemClick = { index ->
            when (index) {
                0 -> Log.d(TAG, "Add Card Clicked")
                1 -> Log.d(TAG, "Remove Card Clicked")
                2 -> Log.d(TAG, "Copy Card Clicked")
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
    }, content = { it ->
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            repeat(5) {
                ColorPaletteCard(text = "Blue",
                    color = Color.Blue,
                    modifier = Modifier.weight(1f),
                    onCardClick = {
                        isSheetPaletteItemsVisible = true
                    },
                    onLockClick = {
                        Log.d(TAG, "Lock Clicked")
                    })
                if (it <= numberOfCards - 1) // Remove spacing on the last card
                    Spacer(modifier = Modifier.height(10.dp))
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = padding, end = padding)
                    .weight(1f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {

                    }, modifier = Modifier
                        .fillMaxWidth(.9f)
                        .padding(end = 10.dp)
                ) {
                    Text(text = "Generate")
                }

                IconButton(onClick = { isMoreOptionSheetVisible = true }) {
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
    cardShape: Shape = RoundedCornerShape(8.dp),
    onCardClick: () -> Unit,
    onLockClick: () -> Unit
) {
    val padding = 16.dp

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = padding, end = padding)
            .clickable { onCardClick() }
            .background(color, shape = cardShape)
            .padding(padding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White,
        )

        IconButton(onClick = { onLockClick() }) {
            Icon(
                imageVector = Icons.Default.LockOpen,
                contentDescription = "Lock Palette",
                tint = Color(255, 255, 255).copy(alpha = .6f),
                modifier = Modifier.size(24.dp)
            )
        }
    }
}


@Preview
@Composable
fun ColorPaletteGeneratorScreenPreview() {
    ColorPaletteGeneratorScreen()
}

