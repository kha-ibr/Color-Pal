package com.example.colorPal.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Palette
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavItems(val route: String, val outlineIcon: ImageVector, val filledIcon: ImageVector, val label: String) {
    data object Generator : NavItems(Screens.GENERATOR.name, Icons.Outlined.Palette, Icons.Rounded.Palette, "Generator")
    data object Favorite :
        NavItems(Screens.FAVORITE.name, Icons.Outlined.FavoriteBorder, Icons.Rounded.Favorite, "Favorite")

    data object Settings : NavItems(Screens.SETTINGS.name, Icons.Outlined.Settings, Icons.Rounded.Settings, "Settings")
}

val navItemList = listOf(
    NavItems.Generator, NavItems.Favorite, NavItems.Settings
)

enum class Screens {
    GENERATOR, FAVORITE, SETTINGS
}
