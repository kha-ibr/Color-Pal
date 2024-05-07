package com.example.colorPal.ui.screens.generate

import android.util.Log
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.colorPal.data.RetrofitInstance
import com.example.colorPal.data.database.ColorInfo
import com.example.colorPal.data.database.ColorRepository
import com.example.colorPal.data.database.Graph
import com.example.colorPal.model.ColorApiResponse
import com.example.colorPal.model.ColorRepresentation
import com.example.colorPal.model.Colors
import kotlinx.coroutines.launch
import kotlin.random.Random


private const val TAG: String = "ViewModel"
private var numberOfColorsToSave = 3

class ColorGeneratorViewModel(private val repository: ColorRepository = Graph.repository) : ViewModel() {
    private val _colorLiveData = MutableLiveData<ColorApiResponse?>()
    val colorLiveData: MutableLiveData<ColorApiResponse?>
        get() = _colorLiveData

    private val _saveFetchedColors = MutableLiveData<MutableList<Colors>?>()
    val saveFetchedColors: MutableLiveData<MutableList<Colors>?>
        get() = _saveFetchedColors

    fun fetchColorScheme(hexCode: String, mode: String) {
        viewModelScope.launch {
            try {
                // Clear existing values before fetching new data
                _colorLiveData.value = null
                _saveFetchedColors.value = null

                val response = RetrofitInstance.colorApiService.getColorsByHex(
                    hex = hexCode,
                    mode = mode,
                )

                _colorLiveData.postValue(response)

                val limitedColors =
                    response.colors?.take(numberOfColorsToSave) as MutableList<Colors>?
                _saveFetchedColors.postValue(limitedColors)

            } catch (e: Exception) {
                Log.e(TAG, "Exception fetching color scheme", e)
            }
        }
    }

    fun addCard() {
        numberOfColorsToSave++
        _saveFetchedColors.postValue(_colorLiveData.value?.colors?.take(numberOfColorsToSave) as MutableList<Colors>?)
    }

    fun removeCard(index: Int) {
        if (_saveFetchedColors.value!!.size > 3) {
            _saveFetchedColors.value!!.removeAt(index)
            numberOfColorsToSave--
        }
    }

    fun copyColorCode(
        index: Int, clipboardManager: ClipboardManager, colorMode: ColorRepresentation
    ) {
        when (colorMode) {
            ColorRepresentation.HEX -> _saveFetchedColors.value?.get(index)?.hex?.clean?.let {
                AnnotatedString(
                    it
                )
            }?.let { clipboardManager.setText(it) }

            ColorRepresentation.RGB -> _saveFetchedColors.value?.get(index)?.rgb?.value?.let {
                AnnotatedString(
                    it
                )
            }?.let { clipboardManager.setText(it) }

            ColorRepresentation.HSL -> _saveFetchedColors.value?.get(index)?.hsl?.value?.let {
                AnnotatedString(
                    it
                )
            }?.let { clipboardManager.setText(it) }

            ColorRepresentation.HSV -> _saveFetchedColors.value?.get(index)?.hsv?.value?.let {
                AnnotatedString(
                    it
                )
            }?.let { clipboardManager.setText(it) }

            ColorRepresentation.CMYK -> _saveFetchedColors.value?.get(index)?.cmyk?.value?.let {
                AnnotatedString(
                    it
                )
            }?.let { clipboardManager.setText(it) }

            ColorRepresentation.NAME -> _saveFetchedColors.value?.get(index)?.name?.value?.let {
                AnnotatedString(
                    it
                )
            }?.let { clipboardManager.setText(it) }
        }
    }

    fun savePalette() {
        val colorToSave = _saveFetchedColors.value ?: return

        val communality = Random.nextInt(0, Int.MAX_VALUE)

        viewModelScope.launch {
            colorToSave.map { color ->
                Log.d(TAG, "${color.xyz?.value}")
                repository.insertColor(
                    ColorInfo(
                        hex = color.hex?.value,
                        commonality = communality
                    )
                )
            }
        }
    }

    fun getHexValue(): String {
        // Generate a random integer between 0 and 0xFFFFFF (inclusive)
        val intValue = Random.nextInt(0x1000000)
        // Convert the integer to uppercase hex string with leading zeros
        return intValue.toString(16).uppercase().padStart(6, '0')
    }
}
