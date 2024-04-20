package com.example.colorPal.model

import kotlinx.serialization.SerialName

enum class ColorRepresentation {
    HEX, RGB, HSL, HSV, CMYK, NAME
}

enum class Mode {
    MONOCHROME, MONOCHROME_DARK,
    MONOCHROME_LIGHT, COMPLEMENT,
    ANALOGIC, ANALOGIC_COMPLEMENT,
    TRIAD, QUAD
}

data class ColorApiResponse(
    val mode: String?,
    val count: String?,
    val colors: List<Colors>?,
    val seed: Seed?,
    val image: Image?,
    @SerialName("_links") val links: Links?,
    @SerialName("_embedded") val embedded: Embedded?
)

data class Colors(
    val hex: Hex?,
    val rgb: Rgb?,
    val hsl: Hsl?,
    val hsv: Hsv?,
    val name: Name?,
    val cmyk: Cmyk?,
    val xyz: XYZ?,
    val image: Image2?,
    val contrast: Contrast?,
    val links: Links2?,
    val embedded: Embedded2?,
)

data class Hex(
    val value: String?, val clean: String?
)

data class Rgb(
    val fraction: FractionRgb?, val r: Int?, val g: Int?, val b: Int?, val value: String?
)

data class FractionRgb(
    val red: Double?, val green: Double?, val blue: Double?
)

data class Hsl(
    val fraction: FractionHsl?, val h: Int?, val s: Int?, val l: Double?, val value: String?
)

data class FractionHsl(
    val h: Double?, val s: Double?, val l: Double?
)

data class Hsv(
    val fraction: FractionHsv?, val value: String?, val h: Int?, val s: Int?, val v: Int?
)

data class FractionHsv(
    val h: Double?, val s: Double?, val v: Double?
)

data class Name(
    val value: String?,
    @SerialName("closest_named_hex") val closestNamedHex: String?,
    @SerialName("exact_match_name") val exactMatchName: Boolean?,
    val distance: Int?
)

data class Cmyk(
    val fraction: FractionCmyk?,
    val value: String?,
    val c: Int?,
    val m: Int?,
    val y: Int?,
    val k: Int?
)

data class FractionCmyk(
    val c: Double?, val m: Double?, val y: Double?, val k: Double?
)

data class XYZ(
    val fraction: FractionXyz?,
    val value: String?,
    @SerialName("X") val x: Int?,
    @SerialName("Y") val y: Int?,
    @SerialName("Z") val z: Int?
)

data class FractionXyz(
    @SerialName("X") val x: Double?,
    @SerialName("Y") val y: Double?,
    @SerialName("Z") val z: Double?
)

data class Image(
    val bare: String?, val named: String?
)

data class Contrast(
    val value: String?
)

data class Links(
    val self: String?, val schemes: Schemes?
)

data class Schemes(
    val monochrome: String?,
    @SerialName("monochrome-dark") val monochromeDark: String?,
    @SerialName("monochrome-light") val monochromeLight: String?,
    val analogic: String?,
    val complement: String?,
    @SerialName("analogic-complement") val analogicComplement: String?,
    val triad: String?,
    val quad: String?
)

data class Embedded(
    val colors: List<Any>?
)

data class Image2(
    val bare: String?, val named: String?
)

data class Links2(
    val self: Self?
)

data class Self(
    val href: String?
)

data class Embedded2(
    val colors: List<Any>?
)

data class Seed(
    val hex: HexSeed?,
    val rgb: RgbSeed?,
    val hsl: HslSeed?,
    val hsv: HsvSeed?,
    val name: NameSeed?,
    val cmyk: CmykSeed?,
    val xyz: XYZSeed?,
    val image: ImageSeed?,
    val contrast: ContrastSeed?,
    @SerialName("_links") val links: LinksSeed?,
    @SerialName("_embedded") val embedded: EmbeddedSeed?
)

data class HexSeed(
    val value: String?, val clean: String?
)

data class RgbSeed(
    val fraction: FractionRgbSeed?, val r: Int?, val g: Int?, val b: Int?, val value: String?
)

data class FractionRgbSeed(
    val red: Double?, val green: Double?, val blue: Double?
)

data class HslSeed(
    val fraction: FractionHslSeed?, val h: Int?, val s: Int?, val l: Double?, val value: String?
)

data class FractionHslSeed(
    val h: Double?, val s: Double?, val l: Double?
)

data class HsvSeed(
    val fraction: FractionHsvSeed?, val value: String?, val h: Int?, val s: Int?, val v: Int?
)

data class FractionHsvSeed(
    val h: Double?, val s: Double?, val v: Double?
)

data class NameSeed(
    val value: String?,
    @SerialName("closest_named_hex") val closestNamedHex: String?,
    @SerialName("exact_match_name") val exactMatchName: Boolean?,
    val distance: Int?
)

data class CmykSeed(
    val fraction: FractionCmykSeed?,
    val value: String?,
    val c: Int?,
    val m: Int?,
    val y: Int?,
    val k: Int?
)

data class FractionCmykSeed(
    val c: Double?, val m: Double?, val y: Double?, val k: Double?
)

data class XYZSeed(
    val fraction: FractionXyzSeed?,
    val value: String?,
    @SerialName("X") val x: Int?,
    @SerialName("Y") val y: Int?,
    @SerialName("Z") val z: Int?
)

data class FractionXyzSeed(
    @SerialName("X") val x: Double?,
    @SerialName("Y") val y: Double?,
    @SerialName("Z") val z: Double?
)

data class ImageSeed(
    val bare: String?, val named: String?
)

data class ContrastSeed(
    val value: String?
)

data class LinksSeed(
    val self: SelfSeed?
)

data class SelfSeed(
    val href: String?
)

data class EmbeddedSeed(
    val colors: List<Any>?
)
