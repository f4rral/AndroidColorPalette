package com.example.colorpalette

import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class ColorPaletteViewModel : ViewModel() {
    private val _redColor: MutableIntState = mutableIntStateOf(0)
    var redColor: Int
        get() = _redColor.intValue
        set(value) {
            _redColor.intValue = value
        }

    private val _greenColor: MutableIntState = mutableIntStateOf(0)
    var greenColor: Int
        get() = _greenColor.intValue
        set(value) {
            _greenColor.intValue = value
        }

    private val _blueColor: MutableIntState = mutableIntStateOf(0)
    var blueColor: Int
        get() = _blueColor.intValue
        set(value) {
            _blueColor.intValue = value
        }

    fun generateRandomColor() {
        _redColor.intValue = Random.nextInt(0, 255)
        _greenColor.intValue = Random.nextInt(0, 255)
        _blueColor.intValue = Random.nextInt(0, 255)
    }
}