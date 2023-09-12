package com.example.android_intern

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showSnackbar(message: String) {
    Snackbar.make(
        this,
        message,
        Snackbar.LENGTH_SHORT
    ).show()
}

fun Photo.Photos.PhotoX.toUrl(): String =
    "https://farm$farm.staticflickr.com/$server/${id}_${secret}_z.jpg"