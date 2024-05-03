package com.example.colorPal.data.database

import kotlinx.coroutines.flow.Flow

class ColorRepository(private val dao: ColorDao) {

    fun getAllColors(): Flow<List<ColorInfo>> = dao.getAllColors()

    suspend fun insertColor(colors: ColorInfo) = dao.insertColor(colors)

    fun updateColors(colors: ColorInfo) = dao.updateColors(colors)

    fun deleteColors(id: Int) = dao.deleteColors(id)
}