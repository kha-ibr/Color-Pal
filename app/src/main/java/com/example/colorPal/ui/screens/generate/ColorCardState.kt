package com.example.colorPal.ui.screens.generate

import androidx.compose.ui.graphics.Color

data class ColorCardState(
    val id: Int,
    val text: String,
    val color: Color,
    val isLocked: Boolean = false
)