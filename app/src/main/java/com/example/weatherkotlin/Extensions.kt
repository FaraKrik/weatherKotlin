package com.example.weatherkotlin

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.util.*

fun Date.isFriday() : Boolean {
    return day == 5
}

fun String.lastChar() = get(length - 1)

fun View.showKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as
            InputMethodManager
    this.requestFocus()
    imm.showSoftInput(this, 0)
}

fun View.hideKeyboard(): Boolean {
    try {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        return inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    } catch (ignored: RuntimeException) { }
    return false
}