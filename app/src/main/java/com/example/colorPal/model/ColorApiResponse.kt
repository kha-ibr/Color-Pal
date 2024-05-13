package com.example.colorPal.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

enum class ColorRepresentation {
    HEX, RGB, HSL, HSV, CMYK, NAME
}

@Serializable
data class ColorApiResponse(
    val mode: String?,
    val count: String?,
    val colors: List<Colors>?,
)

@Serializable
data class Colors(
    val hex: Hex?,
    val rgb: Rgb?,
    val hsl: Hsl?,
    val hsv: Hsv?,
    val name: Name?,
    val cmyk: Cmyk?,
    @SerializedName("XYZ") val xyz: XYZ?,
    val contrast: Contrast?,
)
@Serializable
data class Hex(
    val value: String?, val clean: String?
)
@Serializable
data class Rgb(
    val fraction: FractionRgb?, val r: Int?, val g: Int?, val b: Int?, val value: String?
)
@Serializable
data class FractionRgb(
    val red: Double?, val green: Double?, val blue: Double?
)
@Serializable
data class Hsl(
    val fraction: FractionHsl?, val h: Int?, val s: Int?, val l: Double?, val value: String?
)
@Serializable
data class FractionHsl(
    val h: Double?, val s: Double?, val l: Double?
)
@Serializable
data class Hsv(
    val fraction: FractionHsv?, val value: String?, val h: Int?, val s: Int?, val v: Int?
)
@Serializable
data class FractionHsv(
    val h: Double?, val s: Double?, val v: Double?
)
@Serializable
data class Name(
    val value: String?,
    @SerializedName("exact_match_name") val exactMatchName: Boolean?
)
@Serializable
data class Cmyk(
    val fraction: FractionCmyk?,
    val value: String?,
    val c: Int?,
    val m: Int?,
    val y: Int?,
    val k: Int?
)
@Serializable
data class FractionCmyk(
    val c: Double?, val m: Double?, val y: Double?, val k: Double?
)
@Serializable
data class XYZ(
    val fraction: FractionXyz?,
    val value: String?,
    @SerializedName("X") val x: Int?,
    @SerializedName("Y") val y: Int?,
    @SerializedName("Z") val z: Int?
)
@Serializable
data class FractionXyz(
    @SerializedName("X") val x: Double?,
    @SerializedName("Y") val y: Double?,
    @SerializedName("Z") val z: Double?
)
@Serializable
data class Contrast(
    val value: String?
)