package com.example.colorPal.ui.screens.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.colorPal.data.database.ColorInfo
import com.example.colorPal.data.database.ColorRepository
import com.example.colorPal.data.database.Graph

private const val TAG = "FavoriteViewModel"

class FavoriteViewModel(repository: ColorRepository = Graph.repository) : ViewModel() {

    val allColors: LiveData<List<ColorInfo>> = repository.allColors.asLiveData()

    fun onCardClick(commonality: Int?) {

    }
}
