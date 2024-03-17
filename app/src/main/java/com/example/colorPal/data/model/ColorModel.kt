package com.example.colorPal.data.model

data class ColorModel(
    val hex: Hex,
    val rgb: Rgb,
    val hsl: Hsl,
    val hsv: Hsv,
    val name: Name,
    val cmyk: Cmyk,
    val contrast: Contrast
) {
    data class Hex(
        val value: String,
        val clean: String
    )

    data class Rgb(
        val fraction: Fraction,
        val r: Int,
        val g: Int,
        val b: Int,
        val value: String
    ) {
        data class Fraction(
            val r: Double,
            val g: Double,
            val b: Double
        )
    }

    data class Hsl(
        val fraction: Fraction,
        val h: Int,
        val s: Int,
        val l: Int,
        val value: String
    ) {
        data class Fraction(
            val h: Double,
            val s: Double,
            val l: Double
        )
    }

    data class Hsv(
        val fraction: Fraction,
        val h: Int,
        val s: Int,
        val v: Int,
        val value: String
    ) {
        data class Fraction(
            val h: Double,
            val s: Double,
            val v: Double
        )
    }

    data class Name(
        val value: String,
        val closest_named_hex: String,
        val exact_match_name: Boolean,
        val distance: Int
    )

    data class Cmyk(
        val fraction: Fraction,
        val value: String,
        val c: Int,
        val m: Int,
        val y: Int,
        val k: Int
    ) {
        data class Fraction(
            val c: Double,
            val m: Double,
            val y: Double,
            val k: Double
        )
    }

    data class Contrast(
        val value: String
    )
}
