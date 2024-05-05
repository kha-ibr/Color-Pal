package com.example.colorPal.ui.screens.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.colorPal.data.database.ColorInfo
import com.example.colorPal.data.database.ColorRepository
import com.example.colorPal.data.database.Graph
import kotlinx.coroutines.launch

private const val TAG = "FavoriteViewModel"

class FavoriteViewModel(private val repository: ColorRepository = Graph.repository) : ViewModel() {
    private val _colorList = MutableLiveData<List<ColorInfo>?>()
    val colorList: LiveData<List<ColorInfo>?>
        get() = _colorList

    fun getAllColor() {
        viewModelScope.launch {
            val colors = repository.getAllColors()
            _colorList.postValue(colors)
        }
    }
}
