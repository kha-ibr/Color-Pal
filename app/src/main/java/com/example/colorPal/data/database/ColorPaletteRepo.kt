package com.example.colorPal.data.database

import kotlinx.coroutines.flow.first

class ColorRepository(private val dao: ColorDao) {

    suspend fun getAllColors(): List<ColorInfo> {
        return dao.getAllColors().first() // Assuming only one emission
    }

    suspend fun insertColor(colors: ColorInfo) = dao.insertColor(colors)

    fun updateColors(colors: ColorInfo) = dao.updateColors(colors)

    fun deleteColors(id: Int) = dao.deleteColors(id)
}