package com.example.colorPal.data.database

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class ColorRepository(private val dao: ColorDao) {

    val allColors: Flow<List<ColorInfo>> = dao.getAllColors()

    suspend fun insertColor(colors: ColorInfo) = dao.insertColor(colors)

    fun updateColors(colors: ColorInfo) = dao.updateColors(colors)

    //fun deleteColors(commonality: Int) = dao.deleteColors(commonality)

    suspend fun deleteColors(commonality: Int) {
        withContext(Dispatchers.IO) {
            dao.deleteColors(commonality)
        }
    }
}