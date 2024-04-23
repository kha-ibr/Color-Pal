package com.example.colorPal.ui.component.bottomSheet

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomSheetModel(
    val icon: ImageVector? = null,
    val contentDescription: String? = null,
    val text: String,
    val secondaryIcon: ImageVector? = null
)

