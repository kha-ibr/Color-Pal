package com.example.colorPal.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "color_palette")
data class ColorInfo(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val hex: String?,
    // Assign a unique number to the palette that is generated at the same time.
    val commonality: Int?
)