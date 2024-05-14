package com.example.colorPal

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.LiveData
import com.example.colorPal.data.database.Graph
import com.example.colorPal.ui.navigation.Navigation
import com.example.colorPal.ui.theme.ColorPalTheme

class MainActivity : ComponentActivity() {

    private val themeModeLiveData: LiveData<String> by lazy {
        val sharedPreference = this.getSharedPreferences("my_app_prefs", Context.MODE_PRIVATE)
        sharedPreference.getStringLiveData("theme", null)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Graph.provide(this)

        setContent {
            val themeMode by themeModeLiveData.observeAsState("Auto (Light/Dark)")

            val isDarkTheme = when (themeMode) {
                "Dark" -> true
                "Light" -> false
                else -> isSystemInDarkTheme()
            }

            ColorPalTheme(useDarkTheme = isDarkTheme) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation()
                }
            }
        }
    }

    private fun SharedPreferences.getStringLiveData(
        key: String,
        defValue: String?
    ): LiveData<String> {
        return SharedPreferenceLiveData(this, key, defValue)
    }

    private class SharedPreferenceLiveData(
        private val sharedPreferences: SharedPreferences,
        private val key: String,
        private val defValue: String?
    ) : LiveData<String>() {

        private val preferenceChangeListener =
            SharedPreferences.OnSharedPreferenceChangeListener { _, k ->
                if (k == key) {
                    value = sharedPreferences.getString(key, defValue)
                }
            }

        override fun onActive() {
            super.onActive()
            value = sharedPreferences.getString(key, defValue)
            sharedPreferences.registerOnSharedPreferenceChangeListener(preferenceChangeListener)
        }

        override fun onInactive() {
            super.onInactive()
            sharedPreferences.unregisterOnSharedPreferenceChangeListener(preferenceChangeListener)
        }
    }
}