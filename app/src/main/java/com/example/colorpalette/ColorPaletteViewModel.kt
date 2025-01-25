package com.example.colorpalette

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class ColorPaletteViewModel : ViewModel() {
    private val _currentColor: MutableState<Color> = mutableStateOf(Color.Black)
    val currentColor: Color
        get() = _currentColor.value

    fun generateRandomColor() {
        _currentColor.value = Color(
            red = Random.nextInt(0, 255),
            blue = Random.nextInt(0, 255),
            green = Random.nextInt(0, 255),
        )
    }
}