package com.example.colorPal.ui.screens.generate

import androidx.compose.ui.graphics.Color

data class ColorCardState(
    val id: Int, // Unique identifier for the palette
    val text: String,
    val color: Color,
    val isLocked: Boolean = false // Default unlocked state
)