package com.ozimos.core.presentation.util

import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.ozimos.core.R


fun ImageView.loadImage(image: String) {
    try {
        Glide.with(this.context)
            .load(image)
            .placeholder(R.drawable.place_holder)
            .into(this)
    } catch (e: Exception) {
        Log.e("TAG", "loadImage: ${e.message}")
    }

}