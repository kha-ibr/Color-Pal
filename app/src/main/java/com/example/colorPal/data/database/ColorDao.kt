package com.example.colorPal.data.database

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ColorDao {
    @Query("SELECT * FROM color_palette")
    fun getAllColors(): Flow<List<ColorInfo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = ColorInfo::class)
    suspend fun insertColor(colors: ColorInfo)

    @Update(onConflict = OnConflictStrategy.REPLACE, entity = ColorInfo::class)
    fun updateColors(colors: ColorInfo)

    @Query("DELETE FROM color_palette WHERE id = :id")
    fun deleteColors(id: Int)
}

@Database(entities = [ColorInfo::class], version = 1, exportSchema = false)
abstract class ColorPaletteDatabase : RoomDatabase() {

    abstract fun colorDao(): ColorDao

    companion object {
        @Volatile
        private var INSTANCE: ColorPaletteDatabase? = null

        fun getDatabase(context: Context): ColorPaletteDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ColorPaletteDatabase::class.java,
                    "color_palette"
                ).build()
                INSTANCE = instance

                instance
            }
        }
    }
}

object Graph {
    private lateinit var database: ColorPaletteDatabase

    val repository by lazy {
        ColorRepository(database.colorDao())
    }

    fun provide(context: Context) {
        database = ColorPaletteDatabase.getDatabase(context)
    }
}

