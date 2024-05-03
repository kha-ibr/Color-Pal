package com.example.colorPal.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "color_palette")
data class ColorInfo(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val hex: String?,
    val rgb: String?,
    val hsl: String?,
    val hsv: String?,
    val name: String?,
    @ColumnInfo(name = "exact_match_name") val exactMatchName: Boolean,
    val cmyk: String?,
    val xyz: String?,
    val contrast: String?,
    @ColumnInfo(name = "palette_name") val paletteName: String?,
    // Assign a unique number to the palette that is generated at the same time.
    val commonality: Int?
)