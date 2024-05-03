package com.example.colorPal.ui.screens.favorite

import androidx.lifecycle.ViewModel
import com.example.colorPal.data.database.ColorRepository
import com.example.colorPal.data.database.Graph

private const val TAG = "FavoriteViewModel"

class FavoriteViewModel(private val repository: ColorRepository = Graph.repository) : ViewModel() {

}
