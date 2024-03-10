package com.example.colorPal.data.model

data class ColorModel(
    val hex: String?,
    val rgb: String?,
    val hsl: String?,
    val cmyk: String?,
    val format: String = "json",
    val mode: String = "monochrome",
    val count: Int = 5,
    val name: String
)