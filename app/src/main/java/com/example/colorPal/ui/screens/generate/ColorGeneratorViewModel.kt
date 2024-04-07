package com.example.colorPal.ui.screens.generate

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.lifecycle.ViewModel

class ColorGeneratorViewModel : ViewModel() {


    private val defaultColors = listOf(
        ColorCardState(1, "Color 1", Color.Green),
        ColorCardState(2, "Color 2", Color.Black),
        ColorCardState(3, "Color 3", Color.Blue)
    )

    private val _colorStates = mutableStateListOf(*defaultColors.toTypedArray())
    val colorStates: List<ColorCardState>
        get() = _colorStates

    fun updateColor(cardId: Int, newColor: Color) {
        _colorStates.find { it.id == cardId }?.let { colorState ->
            if (!colorState.isLocked) {
                _colorStates[_colorStates.indexOf(colorState)] = colorState.copy(color = newColor)
            }
        }
    }

    fun toggleLock(cardId: Int) {
        _colorStates.find { it.id == cardId }?.let { colorState ->
            _colorStates[_colorStates.indexOf(colorState)] =
                colorState.copy(isLocked = !colorState.isLocked)
        }
    }

    fun removeCard(cardId: Int) {
        if (_colorStates.size > 2)
            _colorStates.removeAll { it.id == cardId }
    }

    fun addCard() {
        val randomColor = generateRandomColor()
        val newColorCard: ColorCardState

        if (_colorStates.size <= 5) {
            newColorCard = ColorCardState(
                id = generateUniqueId(),
                text = randomColor.value.toString(),
                color = randomColor
            )
            _colorStates.add(newColorCard)
        }
    }

    fun copyColorCode(cardId: Int, clipboardManager: ClipboardManager) {
        _colorStates.find { it.id == cardId }?.let { colorState ->
            val colorCode = colorState.color.toString()
            clipboardManager.setText(AnnotatedString(colorCode))
        }
    }

    private fun generateUniqueId(): Int {
        return _colorStates.maxByOrNull { it.id }?.id?.plus(1) ?: 1
    }

    private fun generateRandomColor(): Color {
        return Color(
            (0..255).random(), (0..255).random(), (0..255).random(), alpha = 1
        )
    }

}
