package com.example.colorPal.ui.screens.generate

sealed class ColorPaletteAction {
    data object AddCard : ColorPaletteAction()
    data object RemoveCard : ColorPaletteAction()
    data object CopyColorCode : ColorPaletteAction()
}